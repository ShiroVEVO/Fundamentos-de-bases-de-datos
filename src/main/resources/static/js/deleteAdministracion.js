function borrarCuenta(id){
    let url = '/cuenta/borrar/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(function(response){
        location.reload();
        return response.json();})
    .then(function(){
        console.log(id);
    })
}

function borrarUsuario(id){
    let url = '/usuario/borrar/' + id;
    const settings = {
         method: 'DELETE'
    }
    fetch(url,settings)
    .then(function(response){
        location.reload();
        return response.json();})
    .then(function(){
        console.log(id);
    })
}

function borrarPlan(id){
    let url = '/plan/borrar/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(function(response){
        location.reload();
        return response.json();})
    .then(function(){
        console.log(id);
    })
}