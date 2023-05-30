function actualizarOdontologo(matri){
    let registro = document.querySelector("#registroOdontologoKey" + matri);
    let divFormulario = document.querySelector("#divOdontologoUpdate");
    let btnUpdate = document.querySelector("#btnUpdate");
    let organizador = [];
    for(let i = 0; i < registro.cells.length - 2; i++){
        divFormulario.childNodes[1][i].value = registro.cells[i].textContent;
    }
    divFormulario.style.display = "block";
    divFormulario.childNodes[1][0].value = matri;
    fetch("/odontologo/"+matri)
    .then(function(response){
        return response.json();})
    .then(function(data){
        organizador.push(data.ROLE);
    });
    btnUpdate.addEventListener("click",function(e){
        e.preventDefault();
        for(let i = 0; i < divFormulario.childNodes[1].length-1; i++){    
            organizador.push(divFormulario.childNodes[1][i].value);
        }
        let url = '/odontologo/actualizar';
        let data = {
            ROLE: organizador[0],
            user: organizador[2],
            password: organizador[3],
            nombre: organizador[4],
            apellido: organizador[5],
            matricula: organizador[1]
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
            let registro_a_actualizar = document.querySelector("#registroOdontologoKey"+data.matricula);
            registro_a_actualizar.innerHTML =
            "<tr id=\"registroOdontologoKey" + data.matricula + "\">" +
                "<th>" + data.matricula + "</th>" +
                "<th>" + data.user + "</th>" +
                "<th>" + data.password + "</th>" +
                "<th>" + data.nombre + "</th>" +
                "<th>" + data.apellido + "</th>" +
                "<th><input type=\"button\" class=\"btn btn-primary btnActualizarOdontologo\" id=\"" + data.matricula + "\" value=\"✏\"></input></th>" +
                "<th><input type=\"button\" class=\"btn btn-danger btnEliminarOdontologo\" id=\"" + data.matricula + "\" value=\"❌\"></input></th>" +
            "</tr>";
            let ocultable = document.querySelector("#divOdontologoUpdate");
            ocultable.style.display = "none";
        })
    })
}

function actualizarPaciente(DNIPaciente){
    let registro = document.querySelector("#registroPacienteKey" + DNIPaciente);
    let divFormulario = document.querySelector("#divPacienteUpdate");
    let btnUpdate = document.querySelector("#btnPacienteUpdate");
    let organizador = [];
    console.log(divFormulario);
    for(let i = 0; i < registro.cells.length - 2; i++){
        divFormulario.childNodes[1][i].value = registro.cells[i].textContent;
    }
    divFormulario.style.display = "block";
    divFormulario.childNodes[1][0].value = DNIPaciente;
    fetch("/paciente/" + DNIPaciente)
    .then(function(response){
        return response.json();})
    .then(function(data){
        organizador.push(data.ROLE);
    });
    btnUpdate.addEventListener("click",function(e){
        e.preventDefault();
        for(let i = 0; i < divFormulario.childNodes[1].length-1; i++){
            organizador.push(divFormulario.childNodes[1][i].value);
        }
        let url = '/paciente/actualizar';
        let data = {
            ROLE: organizador[0],
            user: organizador[2],
            password: organizador[3],
            nombre: organizador[4],
            apellido: organizador[5],
            DNI: organizador[1],
            domicilio: organizador[6],
            fechaAlta: organizador[7]
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
            let registro_a_actualizar = document.querySelector("#registroPacienteKey"+data.DNI);
            registro_a_actualizar.innerHTML =
            "<tr id=\"registroPacienteKey" + data.DNI + "\">" +
                "<th>" + data.DNI + "</th>" +
                "<th>" + data.user + "</th>" +
                "<th>" + data.password + "</th>" +
                "<th>" + data.nombre + "</th>" +
                "<th>" + data.apellido + "</th>" +
                "<th>" + data.domicilio + "</th>" +
                "<th>" + data.fechaAlta + "</th>" +
                "<th><input type=\"button\" class=\"btn btn-primary btnActualizarPaciente\" id=\"" + data.DNI + "\" value=\"✏\"></input></th>" +
                "<th><input type=\"button\" class=\"btn btn-danger btnEliminarPaciente\" id=\"" + data.DNI+ "\" value=\"❌\"></input></th>" +
            "</tr>";
            let ocultable = document.querySelector("#divPacienteUpdate");
            ocultable.style.display = "none";
        })
        location.reload();
    })
}
