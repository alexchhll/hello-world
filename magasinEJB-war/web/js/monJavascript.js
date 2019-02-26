function getXhr(){
    var xhr = null;
   if (window.XMLHttpRequest)
       xhr = new XMLHttpRequest();
   else if (window.ActiveXObject){
       try{
           xhr = new ActiveXObject("Msxml12.XMLHTTP");
       } catch(e){
           xhr = new ActiveXObject("Microsoft.XMLHTTP");
       }
   }
   else{
       alert("Votre navigateur ne prend pas en charge les objets XMLHTTPrequest.....");
       xhr =false;
   }
   return xhr;
}

function actualiserMenuMain(ref){
    var xhr = getXhr();
    var url ="ControleurMain?section=operations-panier&operation=ajouter&ref="+ref+"&origine=ajax";//Ici on est en get en post on aurait récuperer les paramteres en send
    xhr.onreadystatechange = function (){
        if(xhr.readyState == 4 && xhr.status == 200 ){
            var elem = document.getElementById("menuMain");//depuis mise a jour la variable se creer automatiquement elle est id a l'id
            //div02 ---> il est possible que tous le navigateur ne charge pas cette variable c'est un souci de compatiblité.
            var contenu = xhr.responseText;
            //alert(contenu);
            elem.innerHTML = contenu;
        }
    };
    xhr.open("GET",url,true)
    xhr.send(null);
    
    
}
//---------------------------------- Avantage de l'actualisation ajax------------------
// On economise de la bande passante sur les sites lourd avec bcp d'images par ex
// gain enorme de temps du coté BDD ressources et du coté Serveurs evite de réintérroger sur tout
//--> Regles :L'appli doit fonctionné indépendement de java scipt
//Attention les robots de  referencement google ne lis pas les pages ajax ne pas tout coder en ajax est primordial



