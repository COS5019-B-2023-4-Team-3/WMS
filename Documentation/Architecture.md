
<h1>Warehouse Management System Architecture</h1>

<!-- Table of Contents -->
<h2>Contents</h2>
<ul>
    <li><h3><a href="#diagram">Diagram</a></h3></li>
    <li><h3><a href="#persistence-layer">Persistence Layer</a></h3></li>
    <li><h3><a href="#application-layer">Application Layer</a></h3></li>
    <li><h3><a href="#presentation-layer">Presentation Layer</a></h3></li>
    <li><h3><a href="#files">Files</a></h3></li>
</ul>


<h2 id="#diagram">Diagram</h2>
<p align="center">
  <img src="https://github.com/COS5019-B-2023-4-Team-3/WMS/assets/81480788/c353429b-025e-47f0-9f36-adb35e9fa6c6" alt="n-tier diagram" width="500">
</p>



<!-- Persistence Layer -->
<h2 id="persistence-layer">Persistence Layer</h2>
<p>The persistence layer is responsible for storing and retrieving data from the database.</p>
<ul>
  <li>SQL: Manages database queries</li>
  <li>JDBC: Java Database Connectivity for interacting with databases</li>
</ul>


<!-- Application Layer -->
<h2 id="application-layer">Application Layer</h2>
<p>The application layer contains the business logic and handles communication between the persistence and presentation layers.</p>
<ul>
  <li>Spring Boot: Framework for building Java-based applications</li>
  <li>Java: Programming language for implementing business logic</li>
  <li>Apache POI: Library for reading and writing Microsoft Office files</li>
  <li>openPDF: Library for exporting to PDF files</li>
</ul>

<!-- Presentation Layer -->
<h2 id="presentation-layer">Presentation Layer</h2>
<p>The presentation layer is responsible for rendering the user interface and handling user interactions.</p>
<ul>
  <li>HTML: Markup language for structuring web pages</li>
  <li>CSS: Stylesheet language for styling web pages</li>
  <li>JavaScript: Programming language for adding interactivity to web pages</li>
  <li>Bootstrap: Front-end framework for designing responsive and mobile-first websites</li>
  <li>JFreeChart: Library for creating charts and graphs</li>
</ul>

<!-- Files -->
<h2 id="files">Files</h2>
<p>The files section includes the types of files exported by the system.</p>
<ul>
  <li>xlsx: Microsoft Excel files</li>
  <li>csv: Comma-separated values files</li>
  <li>pdf: Portable Document Format files</li>
</ul>




