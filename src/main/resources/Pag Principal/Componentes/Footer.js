import React from "react";
import "./Footer.css"

const Footer = () => {
  return (
    <footer>
      <div className="footer-left">
        TempBici
      </div>
      <div className="footer-middle">
        Hecho por:
        <ul className="author-list">
          <li>Camilo Alejandro Zapata Santofimio - 20211020037</li>
          <li>María Fernanda Pérez Hernández - 20211020002</li>
          <li>Evelyn Michell Diaz Furque - 20202020122</li>
          <li>Santiago Serrano Sánchez - 20201007076</li>
          <li>Adrian Stiven Olmos Ardila - 20181020039</li>
        </ul>
      </div>
      <div className="footer-right">
        Proyecto Fundamentos de Bases de Datos
      </div>
    </footer>
  );
};

export default Footer;