import React from "react";
import "./Planes.css"
import BtnViajesEsporadicos from "./BtnViajeEsporadico";
import BtnPlanDiario from "./BtnPlanDiario";
import BtnPlanMensual from "./BtnPlanMensual";
import BtnPlanAnual from "./BtnPlanAnual";

const Planes = () => {
    return (
      <div className='Planes2'>

      <div className='itemPlan2'>
        <p>Viaje Esporádicos</p>
        <p>Solo podras realizar un viaje</p>
        <ul>
          <li>El valor del desanclaje de la bicicleta mecánica es $1.300 pesos</li>
          <li>El valor del desanclaje de la bicicleta eléctrica es $2.500 pesos</li>
          <li>El viaje tiene una duración de máximo 60 minutos</li>
          <li>El valor por minuto adicional es de $150 pesos</li>
        </ul>

        <BtnViajesEsporadicos />
      </div>

      <div className='itemPlan2'>
        <p>Plan Diario</p>
        <p>Adquiere el plan diario para tener los siguientes beneficios:</p>
        <ul>
          <li>Su valor es de $9.990 más $890 por cada retiro de una bicicleta</li>
          <li>Se podrá realizar hasta 4 viaje de 60 minutos por día</li>
          <li>El valor por minuto adicional es de $150 pesos</li>
          <li>Si se realiza un quinto viaje, tendra un valor adicional de $3.990</li>
        </ul>
        <BtnPlanDiario />

      </div>

      <div className='itemPlan2'>
        <p>Plan Mensual</p>
        <p>Adquiere el plan mensual para tener los siguientes beneficios:</p>
        <ul>
          <li>Su valor es de $31.990 pesos</li>
          <li>Se podrá realizar hasta 4 viajes de 60 minutos</li>
          <li>El desanclaje de las bicicletas eléctricas tendra un valor de $999 pesos</li>
          <li>El valor por minuto adicional es de $75 pesos</li>
        </ul>
        <BtnPlanMensual />
      </div>

      <div className='itemPlan2'>
        <p>Plan Anual</p>
        <p>Adquiere el plan anual para tener los siguientes beneficios:</p>
        <ul>
          <li>Su valor es de $229.900 pesos</li>
          <li>Se podrá realizar hasta 4 viajes de 60 minutos</li>
          <li>El desanclaje de las bicicletas eléctricas tendra un valor de $999 pesos</li>
          <li>El valor por minuto adicional es de $75 pesos</li>
        </ul>
        <BtnPlanAnual />
      </div>

    </div >
    );
};
export default Planes;