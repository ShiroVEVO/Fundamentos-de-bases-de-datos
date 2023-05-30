  window.onload = function(){
    let btnGuardar = document.querySelector("#guardar");
    let data;
    let url;
    btnGuardar.addEventListener("click",function(e){
        e.preventDefault();
        let form = document.querySelector(".sectionIngreso form");
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

        }else if(form[0].id == "IDPlan"){
            data = {
                k_plan: organizador[0],
                tiempoSuscripcion: organizador[1],
                duracionMaxViaje: organizador[2],
                cantidadMaxViajes: organizador[3],
                viajesExtra: organizador[4],
                valorRetiroMecanica: organizador[5],
                valorRetiroElectrica: organizador[6],
                tarifaSuscripcion: organizador[7],
                valorViajeExtra: organizador[8],
                valorMinAdicional: organizador[9],
                nombre: organizador[10],
            }
            url = '/plan/guardar';
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
                fechaNacimiento: organizador[10],
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
            console.log("guardado con exito");
            alert("guardado con exito");
            location.reload();
        })
    })
}



