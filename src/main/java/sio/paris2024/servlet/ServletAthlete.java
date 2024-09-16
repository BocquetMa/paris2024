package sio.paris2024.servlet;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.ServletException;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sio.paris2024.database.DaoAthlete;
import sio.paris2024.database.DaoPays;
import sio.paris2024.form.FormAthlete;
import sio.paris2024.model.Athlete;
import sio.paris2024.model.Pays;

// Ajout des annotations @WebServlet et @MultipartConfig
@WebServlet("/Test2")
public class ServletAthlete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public static final int TAILLE_TAMPON = 10240;

    public static final String CHEMIN_FICHIERS = "E:/Bocquet/paris2024/src/main/webapp/vues/athlete/image/";

    
    public ServletAthlete(){
        super();
    }
    
    Connection cnx ;
            
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        
        System.out.println("SERVLKET CONTEXT=" + servletContext.getContextPath());
        cnx = (Connection)servletContext.getAttribute("connection"); 
        
        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(ServletAthlete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAthlete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletAthlete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getRequestURI();  
       
        // Récup et affichage les athletes 
        if(url.equals("/paris2024/ServletAthlete/lister"))
        {              
            ArrayList<Athlete> lesAthletes = DaoAthlete.getLesAthletes(cnx);
            request.setAttribute("pLesAthletes", lesAthletes);
            //System.out.println("lister eleves - nombres d'élèves récupérés" + lesEleves.size() );
           getServletContext().getRequestDispatcher("/vues/athlete/listerAthletes.jsp").forward(request, response);
        }
        
        if(url.equals("/paris2024/ServletAthlete/consulter"))
        { 
            int idAthlete = Integer.parseInt((String)request.getParameter("idAthlete"));
            Athlete a = DaoAthlete.getAthleteById(cnx, idAthlete);
            request.setAttribute("pAthlete", a);
            //System.out.println("lister eleves - nombres d'élèves récupérés" + lesEleves.size() );
           getServletContext().getRequestDispatcher("/vues/athlete/consulterAthlete.jsp").forward(request, response);
        }
        
          if(url.equals("/paris2024/ServletAthlete/ajouter"))
        {                   
            ArrayList<Pays> lesPays = DaoPays.getLesPays(cnx);
            request.setAttribute("pLesPays", lesPays);
            this.getServletContext().getRequestDispatcher("/vues/athlete/ajouterAthlete.jsp" ).forward( request, response );
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    FormAthlete form = new FormAthlete();
    Athlete ath = form.ajouterAthlete(request);

    Part filePart = request.getPart("fichier"); // Nom du champ dans le formulaire HTML
    if (filePart != null && filePart.getSize() > 0) {
        String fileName = getNomFichier(filePart);
        fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
        
        ecrireFichier(filePart, fileName, CHEMIN_FICHIERS);
        
        ath.setImage(fileName); // Assigner le nom du fichier à l'objet Site
    }

    request.setAttribute("form", form);
    request.setAttribute("pAthlete", ath);

    if (form.getErreurs().isEmpty()) {
        Athlete athleteInsere = DaoAthlete.addAthlete(cnx, ath);
        if (athleteInsere != null) {
            request.setAttribute("pAthlete", athleteInsere);
            this.getServletContext().getRequestDispatcher("/vues/athlete/consulterAthlete.jsp").forward(request, response);
        } else {
            response.sendRedirect("erreur.jsp");
        }
    } else {
        this.getServletContext().getRequestDispatcher("/vues/athlete/ajouterAthlete.jsp").forward(request, response);
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
        this.getServletContext().getRequestDispatcher("/vues/site/ajouterAthlete.jsp").forward(request, response);
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
