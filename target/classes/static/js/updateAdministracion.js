function actualizarCuenta(idCuenta){
    let registro = document.querySelector("#registroCuentaKey" + idCuenta);
    let divFormulario = document.querySelector("#divCuentaUpdate");
    let btnUpdate = document.querySelector("#btnCuentaUpdate");
    let organizador = [];
    for(let i = 0; i < registro.cells.length - 2; i++){
        divFormulario.childNodes[1][i].value = registro.cells[i].textContent;
    }
    divFormulario.style.display = "block";
    divFormulario.childNodes[1][0].value = idCuenta;
    fetch("/cuenta/"+idCuenta)
    .then(function(response){
        return response.json();})
    .then(function(data){
    });
    btnUpdate.addEventListener("click",function(e){
        e.preventDefault();
        for(let i = 0; i < divFormulario.childNodes[1].length-1; i++){    
            organizador.push(divFormulario.childNodes[1][i].value);
        }
        let url = '/cuenta/actualizar';
        let data = {
            k_cuenta: organizador[0],
            plan_k_plan: organizador[1],
            saldoFinal: organizador[2],
            saldoInicial: organizador[3],
            estado: organizador[4],
            contraseña: organizador[5],
            correoElectronico: organizador[6]
        };
        console.log(data);
        const settings = {
            method:'PUT',
            headers: {'Content-Type':'application/json', },
            body: JSON.stringify(data)
        };
        fetch(url,settings)
        .then(function(response){
            return response.json();
        })
        .then(function(data){
            let registro_a_actualizar = document.querySelector("#registroCuentaKey"+data.k_cuenta);
            registro_a_actualizar.innerHTML =
            "<tr idCuenta=\"registroCuentaKey" + data.k_cuenta + "\">" +
                "<th>" + data.k_cuenta + "</th>" +
                "<th>" + data.plan_k_plan + "</th>" +
                "<th>" + data.saldoFinal + "</th>" +
                "<th>" + data.saldoInicial + "</th>" +
                "<th>" + data.estado + "</th>" +
                "<th>" + data.contraseña + "</th>" +
                "<th>" + data.correoElectronico + "</th>" +
                "<th><input type=\"button\" class=\"btn btn-primary btnActualizarCuenta\" idCuenta=\"" + data.k_cuenta + "\" value=\"✏\"></input></th>" +
                "<th><input type=\"button\" class=\"btn btn-danger btnEliminarCuenta\" idCuenta=\"" + data.k_cuenta + "\" value=\"❌\"></input></th>" +
            "</tr>";
            let ocultable = document.querySelector("#divCuentaUpdate");
            ocultable.style.display = "none";
        })
        //location.reload();
    })
}

function actualizarUsuario(idUsuario){
    let registro = document.querySelector("#registroUsuarioKey" + idUsuario);
    let divFormulario = document.querySelector("#divUsuarioUpdate");
    let btnUpdate = document.querySelector("#btnUsuarioUpdate");
    let organizador = [];
    console.log(divFormulario);
    for(let i = 0; i < registro.cells.length - 2; i++){
        divFormulario.childNodes[1][i].value = registro.cells[i].textContent;
    }
    divFormulario.style.display = "block";
    divFormulario.childNodes[1][0].value = idUsuario;
    fetch("/usuario/" + idUsuario)
    .then(function(response){
        return response.json();})
    .then(function(data){
    });
    btnUpdate.addEventListener("click",function(e){
        e.preventDefault();
        for(let i = 0; i < divFormulario.childNodes[1].length-1; i++){
            organizador.push(divFormulario.childNodes[1][i].value);
        }
        let url = '/usuario/actualizar';
        let data = {
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
            sexo: organizador[11]
        };
        const settings = {
            method:'PUT',
            headers: {'Content-Type':'application/json', },
            body: JSON.stringify(data)
        };
        fetch(url,settings)
        .then(function(response){
            return response.json();
        })
        .then(function(data){
            console.log("data: ");
            console.log(data);
            let registro_a_actualizar = document.querySelector("#registroUsuarioKey"+data.identificacion);
            registro_a_actualizar.innerHTML =
            "<tr idUsuario=\"registroUsuarioKey" + data.identificacion + "\">" +
                "<th>" + data.identificacion + "</th>" +    
                "<th>" + data.cuenta_k_cuenta + "</th>" +
                "<th>" + data.numCelular + "</th>" +
                "<th>" + data.tipoIdentificacion + "</th>" +
                "<th>" + data.nacionalidad + "</th>" +
                "<th>" + data.eps + "</th>" +
                "<th>" + data.primerNombre + " " + data.SegundoNombre + "</th>" +
                "<th>" + data.primerApellido + " " + data.SegundoApellido + "</th>" +
                "<th>" + data.fechaNacimiento + "</th>" +
                "<th>" + data.sexo + "</th>" +
                "<th><input type=\"button\" class=\"btn btn-primary btnActualizarUsuario\" idUsuario=\"" + data.identificacion + "\" value=\"✏\"></input></th>" +
                "<th><input type=\"button\" class=\"btn btn-danger btnEliminarUsuario\" idUsuario=\"" + data.identificacion+ "\" value=\"❌\"></input></th>" +
            "</tr>";
            let ocultable = document.querySelector("#divUsuarioUpdate");
            ocultable.style.display = "none";
        })
    })
}

function actualizarPlan(idPlan){
    let registro = document.querySelector("#registroPlanKey" + idPlan);
    let divFormulario = document.querySelector("#divPlanUpdate");
    let btnUpdate = document.querySelector("#btnPlanUpdate");
    let organizador = [];
    for(let i = 0; i < registro.cells.length - 2; i++){
        divFormulario.childNodes[1][i].value = registro.cells[i].textContent;
    }
    divFormulario.style.display = "block";
    divFormulario.childNodes[1][0].value = idPlan;
    fetch("/plan/"+idPlan)
    .then(function(response){
        return response.json();})
    .then(function(data){
    });
    btnUpdate.addEventListener("click",function(e){
        e.preventDefault();
        for(let i = 0; i < divFormulario.childNodes[1].length-1; i++){    
            organizador.push(divFormulario.childNodes[1][i].value);
        }
        let url = '/plan/actualizar';
        let data = {
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
            nombre: organizador[10]
        };
        console.log(data);
        const settings = {
            method:'PUT',
            headers: {'Content-Type':'application/json', },
            body: JSON.stringify(data)
        };
        fetch(url,settings)
        .then(function(response){
            return response.json();
        })
        .then(function(data){
            console.log(data);
            let registro_a_actualizar = document.querySelector("#registroPlanKey"+data.k_Plan);
            registro_a_actualizar.innerHTML =
            "<tr idPlan=\"registroPlanKey" + data.k_plan + "\">" +
                "<th>" + data.k_plan + "</th>" +
                "<th>" + data.valorRetiroMecanica + "</th>" +
                "<th>" + data.valorRetiroElectrica + "</th>" +
                "<th>" + data.tarifaSuscripcion + "</th>" +
                "<th>" + data.tiempoSuscripcion + "</th>" +
                "<th>" + data.duracionMaxViaje + "</th>" +
                "<th>" + data.cantidadMaxViajes + "</th>" +
                "<th>" + data.viajesExtra + "</th>" +
                "<th>" + data.valorViajeExtra + "</th>" +
                "<th>" + data.valorMinAdicional + "</th>" +
                "<th>" + data.nombre + "</th>" +
                "<th><input type=\"button\" class=\"btn btn-primary btnActualizarPlan\" idPlan=\"" + data.k_plan + "\" value=\"✏\"></input></th>" +
                "<th><input type=\"button\" class=\"btn btn-danger btnEliminarPlan\" idPlan=\"" + data.k_plan + "\" value=\"❌\"></input></th>" +
            "</tr>";
            let ocultable = document.querySelector("#divPlanUpdate");
            ocultable.style.display = "none";
        })
        //location.reload();
    })
}