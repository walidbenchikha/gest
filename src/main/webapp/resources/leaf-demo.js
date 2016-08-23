var polyline ;
var Mar;
var mark;
var markers =[];

//var stat =[];
var points =[];
var map = L.map( 'map', {
    center: [20.0, 5.0],
    layers: MQ.mapLayer(),
    minZoom: 2,
    zoom: 2
});



var myURL = jQuery( 'script[src$="leaf-demo.js"]' ).attr( 'src' ).replace( 'leaf-demo.js', '' );

var orangeIcon = L.icon({
    iconUrl: myURL + 'images/orange.png',
    //iconRetinaUrl: myURL + 'images/pin48.png',
    iconSize: [29, 24],
    iconAnchor: [9, 21],
    popupAnchor: [0, -14]
});
var mtnIcon = L.icon({
    iconUrl: myURL + 'images/mtn.png',
    //iconRetinaUrl: myURL + 'images/pin48.png',
    iconSize: [29, 24],
    iconAnchor: [9, 21],
    popupAnchor: [0, -14]
});
var moovIcon = L.icon({
    iconUrl: myURL + 'images/moov.png',
    //iconRetinaUrl: myURL + 'images/pin48.png',
    iconSize: [29, 24],
    iconAnchor: [9, 21],
    popupAnchor: [0, -14]
});


function removetrack(){
	map.removeLayer(polyline);
}

function removeAllMarkers(){
    map.removeLayer(Mar);
}