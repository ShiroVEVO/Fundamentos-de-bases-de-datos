import React, { useState } from "react";
import "./Menu.css"



const Navbar = () => {
    const [isOpen, setIsOpen] = useState(false)
    return (
        <nav className="menu">
            <div className="menu_logo"> TempBici </div>
            <nav className={`menu_items ${isOpen && "open"}`}>
                <a href="/Planes">PLANES</a>
                <a href="#">INICIAR VIAJE</a>
                <a href="#">HISTORIAL</a>
                <a href="#">INICIAR SESIÓN</a>
                <a href="#">REGISTRASE</a>
            </nav>
            <div className={`menu_toggle ${isOpen && "open"}`} onClick={() => setIsOpen(!isOpen)} >
                <span></span>
                <span></span>
                <span></span>
            </div>
        </nav>
    )
}
export default Navbar