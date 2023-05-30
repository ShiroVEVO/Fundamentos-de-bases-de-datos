import React from 'react';
import "./Boton.css"

const BtnViajesEsporadicos = () => {
  const handleButtonClick = () => {
    const PlanJSON = {
        k_plan:1,  
        tiempoSuscripcion: null,
        duracionMaxViaje:60,
        cantidadMaxViajes:1,
        viajesExtra:0,
        valorRetiroMecanica:1300,
        valorRetiroElectrica:2500,
        tarifaSuscripcion:null,
        valorViajeExtra:0,
        valorMinAdicional:150,
        nombre:'Viaje esporádico'
    };

    fetch('http://localhost:8080/enviar-objeto', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(PlanJSON)
    })
      .then(response => response.text())
      .then(data => {
        console.log('Respuesta del servidor:', data);
      })
      .catch(error => {
        console.error('Error al enviar el objeto:', error);
      });
  };

  return (
    <div>
      <button className='boton'onClick={handleButtonClick}>Comprar Viaje</button>
    </div>
  );
};

export default BtnViajesEsporadicos;