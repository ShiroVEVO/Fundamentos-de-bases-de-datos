import React from 'react';
import './App.css';
import Menu from './Componentes/Menu'
import Footer from './Componentes/Footer'
import './Componentes/Planes'



function App() {
  return (
    <div className="container">
      <div className="App">
        <Menu />
      </div>
      <div className='App'>
        <h2>Bienvenidos a TempBici</h2>
      </div>

      <div className="dos-columnas">
        <div className="titulo-confi">
          <h2 className="titulo">SOBRE NOSOTROS</h2>
        </div>
        <div className="descrip-confi">
          <p className="descripcion">
            Somos una empresa que ofrece una alternativa para movilizarse en la ciudad de Bogotá,
            por lo cual se hemos implementado 300 estaciones distribuidas en 6 localidades y más de
            3300 bicicletas
          </p>
        </div>

      </div>

      <div className='dos-columnas'>
        <div className='descrip-confi'>
          <p className='descripcion'>Actualemte contamos con 4 tipos de bicicletas</p>
          <ul>
            <li>Bicicletas mecánicas</li>
            <li>Bicicletas eléctricas</li>
            <li>Monocletas (para personas con movilidad reducida)</li>
            <li>Bicicletas de carga</li>
          </ul>
          <p className='descripcion'>Algunas bicicletas cuentan con sillas para transportar niños</p>
        </div>
        <div className='titulo-confi'>
          <h2 className='titulo'>NUESTRAS BICICLETAS</h2>
        </div>
      </div>

      <div className='dos-columnas'>
        <div className='titulo-confi'>
          <h2 className='titulo'>HORARIOS Y LOCALIDADES</h2>
        </div>
        <div className='descrip-confi'>
          <p className='descripcion'>Actualemte el sistema opera todos los días de la semana entre las 5 a.m y las 10 p.m</p>
          <p className='descipcion'>Las estaciones estan ubicadas en las siguientes localidades: </p>
          <ul>
            <li>Usaquén</li>
            <li>Chapinero</li>
            <li>Teusaquillo</li>
            <li>La Candelaria</li>
            <li>Barrios Unidos</li>
            <li>Engativá</li>
          </ul>
        </div>
      </div>

      <div className='dos-columnas'>
        <div className='descrip-confi'>
          <p className='descripcion'>
            Contamos ciclotalleres y cicloparqueaderos. Tambien disponemos de 3 planes para que disfrutes de nuestro
            servicio y aproveches sus beneficios o bien puedes optar por la opción de Viajes Esporádicos donde solo
            se cobra el desanclaje de las bicicletas y el valor extra del tiempo excedido.
            El pago del plan se hace con tarjeta de crédito o débito.
          </p>
        </div>

        <div className='titulo-confi'>
          <h2 className="titulo">NUESTROS SERVICIOS</h2>
        </div>
      </div>

      <div className='App'>
        <h2>Conoce Nuestros Planes</h2>
      </div>

      <div className='Planes'>

        <div className='itemPlan'>
          <p>Plan Diario</p>
          <p>Adquiere el plan diario para tener los siguientes beneficios:</p>
          <ul>
            <li>Su valor es de $9.990 más $890 por cada retiro de una bicicleta</li>
            <li>Se podrá realizar hasta 4 viaje de 60 minutos por día</li>
            <li>El valor por minuto adicional es de $150 pesos</li>
            <li>Si se realiza un quitno viaje, tendra un valor adicional de $3.990</li>
          </ul>


        </div>

        <div className='itemPlan'>
          <p>Plan Mensual</p>
          <p>Adquiere el plan mensual para tener los siguientes beneficios:</p>
          <ul>
            <li>Su valor es de $31.990 pesos</li>
            <li>Se podrá realizar hasta 4 viajes de 60 minutos</li>
            <li>El desanclaje de las bicicletas eléctricas tendra un valor de $999 pesos</li>
            <li>El valor por minuto adicional es de $75 pesos</li>
          </ul>

        </div>

        <div className='itemPlan'>
          <p>Plan Anual</p>
          <p>Adquiere el plan anual para tener los siguientes beneficios:</p>
          <ul>
            <li>Su valor es de $229.900 pesos</li>
            <li>Se podrá realizar hasta 4 viajes de 60 minutos</li>
            <li>El desanclaje de las bicicletas eléctricas tendra un valor de $999 pesos</li>
            <li>El valor por minuto adicional es de $75 pesos</li>
          </ul>
        </div>

      </div >

      <div className="App">
        <Footer />
      </div>
    </div>
  );
}


export default App;