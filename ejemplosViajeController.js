
// EST con bicis
let headersList = {
    "Accept": "*/*",
    "User-Agent": "Thunder Client (https://www.thunderclient.com)"
}

let response = await fetch("http://localhost:8080/viaje/estacionesBicicleta", { 
    method: "GET",
    headers: headersList
});

let data = await response.text();
console.log(data);
   
// EST con espacio pa guardar la bici
let headersList = {
    "Accept": "*/*",
    "User-Agent": "Thunder Client (https://www.thunderclient.com)"
}

let response = await fetch("http://localhost:8080/viaje/estacionesEspacio", { 
    method: "GET",
    headers: headersList
});

let data = await response.text();
console.log(data);
// Inicio viaje
let headersList = {
    "Accept": "*/*",
    "User-Agent": "Thunder Client (https://www.thunderclient.com)",
    "Content-Type": "application/json"
}

let bodyContent = JSON.stringify([1, 1]);
// Recibe arreglo, primer index: cuenta, segundo: estacionsalida.id

let response = await fetch("http://localhost:8080/viaje/iniciar", { 
    method: "POST",
    body: bodyContent,
    headers: headersList
});

let data = await response.text();
console.log(data);
   //Resultado:
   a = {
    "k_viaje": 1,
    "cuenta_k_cuenta": 1,
    "f_entrega": null,
    "f_desbloqueo": "2023-05-31T09:05:28.456+00:00",
    "costo": 0.0
  }

// Terminar viaje
