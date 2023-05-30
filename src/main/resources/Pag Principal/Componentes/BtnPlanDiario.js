import React from 'react';
import "./Boton.css"

const BtnPlanDiario = () => {
  const handleButtonClick = () => {
    const PlanJSON = {
      k_plan:2,  
      tiempoSuscripcion: 1,
      duracionMaxViaje:60,
      cantidadMaxViajes:4,
      viajesExtra:1,
      valorRetiroMecanica:890,
      valorRetiroElectrica:890,
      tarifaSuscripcion:9900,
      valorViajeExtra:3990,
      valorMinAdicional:150,
      nombre:'Plan Diario'
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

export default BtnPlanDiario;