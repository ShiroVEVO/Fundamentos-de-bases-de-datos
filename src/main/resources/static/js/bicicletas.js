function cargarInicial(){
    fetch('/bicicleta')
    .then(data => data.json())
    .then((data)=>{
       console.log(data)
    })
}

document.addEventListener('DOMContentLoaded', ()=>{
    console.log("cargado")
    cargarInicial();
});


function actualizarSelect(ciudades){
    let select = document.querySelector("#select_ciudad");
    select.innerHTML = ""; // Vacia el select
    ciudades.forEach((ciudad)=>{
        let option = document.createElement('option');
        option.value = ciudad.idciudad;
        option.textContent = ciudad.nombre;
        select.appendChild(option);
    })
}

//TODO actualizar la ciduad especificada