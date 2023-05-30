import React from 'react';
import "./Boton.css"

const BtnPlanMensual = () => {
  const handleButtonClick = () => {
    const PlanJSON = {
        k_plan:3,  
        tiempoSuscripcion: 30,
        duracionMaxViaje:60,
        cantidadMaxViajes:4,
        viajesExtra:1,
        valorRetiroMecanica:890,
        valorRetiroElectrica:999,
        tarifaSuscripcion:31990,
        valorViajeExtra:3990,
        valorMinAdicional:75,
        nombre:'Plan Mensual'
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
      <button className='boton'onClick={handleButtonClick}>Comprar Plan</button>
    </div>
  );
};

export default BtnPlanMensual;