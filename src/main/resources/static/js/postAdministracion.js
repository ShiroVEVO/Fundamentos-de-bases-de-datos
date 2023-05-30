window.onload = function(){
    let btnGuardar = document.querySelector("#guardar");
    let data;
    let url;
    btnGuardar.addEventListener("click",function(e){
        e.preventDefault();
        let form = document.querySelector(".sectionIngreso form");
        console.log(form);
        let organizador = [];
        for(let i = 0; i < form.length-1; i++){
            organizador.push(form[i].value);
        }
        
        if(form[0].id == "IDCuenta"){
            data = {
                k_cuenta: organizador[0],
                plan_k_plan: organizador[1],
                saldoFinal: organizador[2],
                saldoInicial: organizador[3],
                estado: organizador[4],
                contraseña: organizador[5],
                correoElectronico: organizador[6]
            }
            url = '/cuenta/guardar';
        }else{
            data = {
                identificacion: organizador[0],
                cuenta_k_cuenta: organizador[1],
                numCelular: organizador[2],
                tipoIdentificacion: organizador[3],
                nacionalidad: organizador[4],
                eps: organizador[5],
                primerNombre: organizador[6],
                segundoNombre: organizador[7],
                primerApellido: organizador[8],
                segundoApellido: organizador[9],
                fechaNacimiento: obtenerTimestamp(organizador[10]),
                sexo: organizador[11],
            }
            url = '/usuario/guardar';
        }
        let settings = {
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        console.log(url);
        console.log(settings);
        fetch(url,settings)
        .then(function(response){
            return response.json();
        })
        .then(function(data){
            alert("guardado con exito");
            location.reload();
        })
    })
}

function obtenerTimestamp() {
    var fechaInput = document.getElementById("fecha").value;
    var fecha = new Date(fechaInput);
    var timestamp = fecha.getTime() / 1000; // Convertir a segundos dividido por 1000
    console.log(timestamp);
  }

