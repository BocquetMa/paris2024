<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Site"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PARIS 2024</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <style>
        body {
            padding-top: 50px;
            background-color: #f5f5f5;
        }
        .special {
            padding-top: 50px;
        }
        .site-card {
            text-align: center;
            margin-bottom: 30px;
        }
        .site-image {
            width: 220px;
            height: 150px;
            border-radius: 10%;
            object-fit: cover;
            cursor: pointer;
            transition: transform 0.3s ease;
        }
        .site-image:hover {
            transform: scale(1.1);
        }
        .site-name {
            font-weight: bold;
        }
        .site-city {
            color: #555;
        }
        .container {
            margin: 0 auto;
            max-width: 1200px;
        }
        nav div h1 {
            border: 0px;
            font-style: inherit;
            font-variant: inherit;
            font-stretch: inherit;
            font-optical-sizing: inherit;
            font-size-adjust: inherit;
            font-kerning: inherit;
            font-feature-settings: inherit;
            font-variation-settings: inherit;
            margin: 0px;
            padding: 0px;
            vertical-align: baseline;
            color: rgb(256, 256, 256);
            font-family: "Paris 2024", "Olympic Headline", Helvetica, sans-serif;
            font-size: 3.6rem;
            font-weight: 900;
            letter-spacing: 0.018rem;
            line-height: 4rem;
            text-transform: uppercase;
            padding-top: 200px;
            padding-left: 800px;
        }
        /* Burger Menu */
        .burger-menu {
            position: fixed;
            top: 20px;
            left: 20px;
            width: 40px;
            height: 30px;
            cursor: pointer;
            z-index: 1000;
        }
        .burger-menu div {
            width: 100%;
            height: 5px;
            background-color: black;
            margin: 6px 0;
            transition: 0.4s;
        }
        /* Sidebar Navigation */
        .sidebar {
            position: fixed;
            top: 0;
            left: -250px;
            width: 250px;
            height: 100%;
            background-color: #333;
            color: white;
            overflow-x: hidden;
            transition: 0.4s;
            padding-top: 60px;
        }
        .sidebar.active {
            left: 0;
        }
        .sidebar a {
            padding: 10px 15px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
            transition: 0.3s;
        }
        .sidebar a:hover {
            background-color: #575757;
        }

        /* Right Sub-menu */
        .sub-menu-container {
            display: none;
            position: fixed;
            top: 0;
            right: -250px;
            width: 250px;
            height: 100%;
            background-color: #f0f0f0;
            color: #333;
            overflow-x: hidden;
            transition: 0.4s;
            padding-top: 60px;
            z-index: 1001;
        }
        .sub-menu-container.active {
            right: 0;
        }
        .sub-menu-container a {
            padding: 10px 15px;
            text-decoration: none;
            font-size: 16px;
            color: #333;
            display: block;
            transition: 0.3s;
        }
        .sub-menu-container a:hover {
            background-color: #ddd;
        }

        /* Arrow button style */
        .category {
            padding: 10px 15px;
            font-size: 18px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            cursor: pointer;
        }
        .arrow-btn {
            padding: 5px 10px;
            background-color: #575757;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .arrow-btn:hover {
            background-color: #333;
        }
    </style>
</head>
<body style="padding: 0; margin: 0;">

    <!-- Burger Menu -->
    <div class="burger-menu" id="burgerMenu">
        <div></div>
        <div></div>
        <div></div>
    </div>

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="category" data-menu="sports">Sports
            <button class="arrow-btn">➔</button>
        </div>
        <div class="category" data-menu="athletes">Athlètes
            <button class="arrow-btn">➔</button>
        </div>
        <div class="category" data-menu="sites">Sites
            <button class="arrow-btn">➔</button>
        </div>
    </div>

    <!-- Sub-menu containers -->
    <div class="sub-menu-container" id="sportsMenu">
        <a href="https://fr.wikipedia.org/wiki/Athl%C3%A9tisme_au_Jeux_olympiques">Athlétisme</a>
        <a href="https://fr.wikipedia.org/wiki/Natation_au_Jeux_olympiques">Natation</a>
        <a href="https://fr.wikipedia.org/wiki/Cyclisme_au_Jeux_olympiques">Cyclisme</a>
        <a href="https://fr.wikipedia.org/wiki/Gymnastique_au_Jeux_olympiques">Gymnastique</a>
    </div>

    <div class="sub-menu-container" id="athletesMenu">
        <a href="https://fr.wikipedia.org/wiki/Usain_Bolt">Usain Bolt</a>
        <a href="https://fr.wikipedia.org/wiki/Michael_Phelps">Michael Phelps</a>
        <a href="https://fr.wikipedia.org/wiki/Nadia_Comaneci">Nadia Comaneci</a>
        <a href="https://fr.wikipedia.org/wiki/Simone_Biles">Simone Biles</a>
    </div>

    <div class="sub-menu-container" id="sitesMenu">
        <a href="https://fr.wikipedia.org/wiki/Stade_de_France">Stade de France</a>
        <a href="https://fr.wikipedia.org/wiki/Parc_des_Princes">Parc des Princes</a>
        <a href="https://fr.wikipedia.org/wiki/Bercy_Ar%C3%A8na">Bercy Arena</a>
        <a href="https://fr.wikipedia.org/wiki/Grand_Palais">Grand Palais</a>
    </div>

    <!-- Overlay (to close sidebar) -->
    <div class="overlay" id="overlay"></div>

    <nav style="top:0;">
        <div style="position:absolute;">
            <h1>LISTE DES SITES</h1>
        </div>
        <img src="../vues/image/fondJO.png" alt="alt" style="width:100%;"/>
    </nav> 

    <div class="container special">
        <br><br>
        <div class="row">
            <%
                ArrayList<Site> lesSites = (ArrayList)request.getAttribute("sLesSites");
                int siteCount = 0;
                for (Site s : lesSites) {
                    if (siteCount % 8 == 0 && siteCount != 0) {
                        out.println("</div><div class='row'>");
                    }

                    String imageName = s.getImage();
                    String imageUrl = (imageName != null && !imageName.isEmpty()) ? 
                                       request.getContextPath() + "/vues/image/" + imageName : 
                                       "https://via.placeholder.com/150";
            %>
            <div class="col-xs-6 col-sm-4 col-md-3">
                <div class="site-card">
                    <a href="../ServletSite/consulter?idSite=<%= s.getId() %>" target="_blank">
                        <img src="<%= imageUrl %>" alt="Image de <%= s.getNom() %>" class="site-image" />
                    </a>
                    
                    <div class="site-name"><%= s.getNom() %></div>
                    <div class="site-city"><%= s.getVille() %></div>
                </div>
            </div>
            <%
                    siteCount++;
                }
            %>
        </div>
    </div>

    <script>
        // Sidebar toggle
        const burgerMenu = document.getElementById('burgerMenu');
        const sidebar = document.getElementById('sidebar');
        const subMenus = document.querySelectorAll('.sub-menu-container');

        // Open/close sidebar
        burgerMenu.addEventListener('click', () => {
            sidebar.classList.toggle('active');
        });

        // Click on arrow to show right sub-menu
        const categories = document.querySelectorAll('.category');
        categories.forEach(category => {
            category.querySelector('.arrow-btn').addEventListener('click', () => {
                const menuId = category.getAttribute('data-menu') + 'Menu';
                const menu = document.getElementById(menuId);

                // Close other menus
                subMenus.forEach(subMenu => {
                    if (subMenu !== menu) {
                        subMenu.classList.remove('active');
                    }
                });

                // Toggle the current menu
                menu.classList.toggle('active');
            });
        });
    </script>
</body>
</html>
