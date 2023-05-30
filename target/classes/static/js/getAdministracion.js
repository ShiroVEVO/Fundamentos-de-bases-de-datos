window.onload = function(){
    
fetch("/cuenta")
.then(function(response){
    return response.json();})
.then(function(data){
    let tablaCuentas = document.querySelector("#BodyTablaCuentas");
    console.log(tablaCuentas);
    for(let i = 0; i < data.length; i++){
        tablaCuentas.innerHTML +=
        "<tr id=\"registroCuentaKey" + data[i].k_cuenta + "\">" +
            "<th>" + data[i].plan_k_plan + "</th>" +
            "<th>" + data[i].saldoFinal + "</th>" +
            "<th>" + data[i].saldoInicial + "</th>" +
            "<th>" + data[i].estado + "</th>" +
            "<th>" + data[i].contraseña + "</th>" +
            "<th>" + data[i].correoElectronico + "</th>" +
            "<th><input type=\"button\" class=\"btn btn-primary btnActualizarCuenta\" id=\"" + data[i].plan_k_plan + "\" value=\"✏\"></input></th>" +
            "<th><input type=\"button\" class=\"btn btn-danger btnEliminarCuenta\" id=\"" + data[i].plan_k_plan + "\" value=\"❌\"></input></th>" +
        "</tr>";
    }
    let botonesEliminarCuenta = document.querySelectorAll('.btnEliminarCuenta');
    for(let i = 0; i < botonesEliminarCuenta.length; i++){
        botonesEliminarCuenta[i].addEventListener("click",function(){
            borrarCuenta(botonesEliminarCuenta[i].attributes[2].value);
        });
    }
    let botonesActualizarCuenta = document.querySelectorAll('.btnActualizarCuenta');
        for(let i = 0; i < botonesActualizarCuenta.length; i++){
            botonesActualizarCuenta[i].addEventListener("click",function(){
                actualizarCuenta(botonesActualizarCuenta[i].attributes[2].value);
            });
        }
})

fetch("/usuario")
.then(function(response){
    return response.json();})
.then(function(data){
    let tablaPacientes = document.querySelector("#BodyTablaPacientes");
    for(let i = 0; i < data.length; i++){
        tablaPacientes.innerHTML +=
        "<tr id=\"registroPacienteKey" + data[i].DNI + "\">" +
            "<th>" + data[i].DNI + "</th>" +
            "<th>" + data[i].user + "</th>" +
            "<th>" + data[i].password + "</th>" +
            "<th>" + data[i].nombre + "</th>" +
            "<th>" + data[i].apellido + "</th>" +
            "<th>" + data[i].domicilio + "</th>" +
            "<th>" + data[i].fechaAlta + "</th>" +
            "<th><input type=\"button\" class=\"btn btn-primary btnActualizarPaciente\" id=\"" + data[i].DNI + "\" value=\"✏\"></input></th>" +
            "<th><input type=\"button\" class=\"btn btn-danger btnEliminarPaciente\" id=\"" + data[i].DNI + "\" value=\"❌\"></input></th>" +
        "</tr>";
    }
    let botonesEliminarPacientes = document.querySelectorAll('.btnEliminarPaciente');
    for(let i = 0; i < botonesEliminarPacientes.length; i++){
        botonesEliminarPacientes[i].addEventListener("click",function(){
            borrarPaciente(botonesEliminarPacientes[i].attributes[2].value);
        });
    }
    let botonesActualizarPaciente = document.querySelectorAll('.btnActualizarPaciente');
        for(let i = 0; i < botonesActualizarPaciente.length; i++){
            botonesActualizarPaciente[i].addEventListener("click",function(){
                actualizarPaciente(botonesActualizarPaciente[i].attributes[2].value);
            });
        }
})
}
