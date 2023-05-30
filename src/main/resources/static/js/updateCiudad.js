document.addEventListener('DOMContentLoaded', ()=>{
    cargarInicial();
});

ciudades = [];

function cargarInicial(){
    fetch('/ciudad')
    .then(data => data.json())
    .then((data)=>{
        actualizarSelect(data);
    })
    .catch((e)=>{
        alert("Error al cargar las ciudades");
        actualizarSelect([]);
    });

    document.querySelector("#form").onsubmit = function(e){
        e.preventDefault();
        
        let ciudadAModificar = ciudades[this[0].selectedIndex];
        
        let [hora, minuto] = this[1].value.split(':');
        ciudadAModificar.inicioServicio.setHours(hora, minuto);
        [hora, minuto] = this[2].value.split(':');
        ciudadAModificar.finalServicio.setHours(hora, minuto);
        
        copia = JSON.parse(JSON.stringify(ciudadAModificar))

        copia.finalServicio = ciudadAModificar.finalServicio.getTime();
        copia.inicioServicio = ciudadAModificar.inicioServicio.getTime();

        console.log(copia)
        postUpdate(copia);
    }
}

function postUpdate(ciudad){
    let settings = {
        method:'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(ciudad)
    };
    console.log(settings);
    fetch('/ciudad/actualizar',settings)
        .then(r => r.status == 200 ? r.json() : Promise.reject(':)'))
        .then(()=>{
            alert('Ciudad actualizada');
        })
        .catch(()=>{
            alert("Error al modificar la ciudad");
        });
}

function actualizarSelect(_ciudades){
    ciudades = _ciudades;
    let select = document.querySelector("#select_ciudad");

    select.onchange = (e)=>{
        let inicio = _ciudades[select.selectedIndex].inicioServicio;
        let fin = _ciudades[select.selectedIndex].finalServicio;

        document.querySelector("#hora_inicio").value = inicio.toTimeString().substring(0, 8);
        document.querySelector("#hora_cierre").value = fin.toTimeString().substring(0, 8);
    }
    select.innerHTML = ""; // Vacia el select
    
    if(_ciudades.length == 0){
        let option = document.createElement('option');
        option.value = "0";
        option.textContent = "ERORR";
        select.appendChild(option);
    }
    _ciudades.map((ciudad, indice)=>{
        ciudad.inicioServicio = new Date(ciudad.inicioServicio);
        ciudad.finalServicio = new Date(ciudad.finalServicio);
        let option = document.createElement('option');
        option.value = indice+"-"+ciudad.idciudad;
        option.textContent = ciudad.nombre;
        select.appendChild(option);
        return ciudad;
    })

    console.log(_ciudades)

    select.onchange();
}