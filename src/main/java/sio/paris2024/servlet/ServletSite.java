package sio.paris2024.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import sio.paris2024.database.DaoSite;
import sio.paris2024.form.FormSite;
import sio.paris2024.model.Site;

// Ajout des annotations @WebServlet et @MultipartConfig
@WebServlet("/Test")
public class ServletSite extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public static final int TAILLE_TAMPON = 10240;

    public static final String CHEMIN_FICHIERS = "E:/Bocquet/paris2024/src/main/webapp/vues/image/";

    
    public ServletSite(){
        super();
    }
    
    
    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI().toLowerCase();
        String[] args = url.split("/");

        if (args[3].equals("lister")) {
            ArrayList<Site> lesSites = DaoSite.getLesSites(cnx);
            request.setAttribute("sLesSites", lesSites);
            getServletContext().getRequestDispatcher("/vues/site/listerSites.jsp").forward(request, response);
        } else if (args[3].equals("consulter")) {
            int idSite = Integer.parseInt(request.getParameter("idSite"));
            Site s = DaoSite.getSiteById(cnx, idSite);
            request.setAttribute("pSite", s);
            getServletContext().getRequestDispatcher("/vues/site/consulterSite.jsp").forward(request, response);
        } else if (args[3].equals("ajouter")) {
            this.getServletContext().getRequestDispatcher("/vues/site/ajouterSite.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        FormSite form = new FormSite();
    Site sit = form.ajouterSite(request);

    Part filePart = request.getPart("fichier"); // Nom du champ dans le formulaire HTML
    if (filePart != null && filePart.getSize() > 0) {
        String fileName = getNomFichier(filePart);
        fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
        
        ecrireFichier(filePart, fileName, CHEMIN_FICHIERS);
        
        sit.setImage(fileName); // Assigner le nom du fichier à l'objet Site
    }

    request.setAttribute("form", form);
    request.setAttribute("pSite", sit);

    if (form.getErreurs().isEmpty()) {
        Site siteInsere = DaoSite.addSite(cnx, sit);
        if (siteInsere != null) {
            request.setAttribute("pSite", siteInsere);
            this.getServletContext().getRequestDispatcher("/vues/site/consulterSite.jsp").forward(request, response);
        } else {
            response.sendRedirect("erreur.jsp");
        }
    } else {
        this.getServletContext().getRequestDispatcher("/vues/site/ajouterSite.jsp").forward(request, response);
    }
        

        
        String description = request.getParameter("description");
        request.setAttribute("description", description);
        
        Part part = request.getPart("fichier");
        String nomFichier = getNomFichier(part);
        
        if(nomFichier != null && !nomFichier.isEmpty()){
            String nomChamp = part.getName();
            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);
            
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);
            
            request.setAttribute(nomChamp, nomFichier);
        }
        this.getServletContext().getRequestDispatcher("/vues/site/ajouterSite.jsp").forward(request, response);
    }

    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);

            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }

            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }   

        

    @Override
    public String getServletInfo() {
        return "Servlet for managing site information and image uploads";
    }

}

