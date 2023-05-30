function borrarOdontologo(matricula){
    let url = '/odontologo/borrar/' + matricula;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(function(response){
        location.reload();
        return response.json();})
    .then(function(){
        console.log(matricula);
    })
}

function borrarPaciente(DNI){
    let url = '/paciente/borrar/' + DNI;
    const settings = {
         method: 'DELETE'
    }
    fetch(url,settings)
    .then(function(response){
        location.reload();
        return response.json();})
    .then(function(){
        console.log(DNI);
    })
}