var req;
var my_divid;
var moz = 0;
var appserver_url = "http://imbi.ld.ttu.ee:7500/Tool/";

function Initialize_dc()
{
    try
    {
        req=new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch(e)
    {
        try
        {
            req=new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(oc)
        {
            req=null;
        }
    }

    if(!req&&typeof XMLHttpRequest!="undefined")
    {
        req= new XMLHttpRequest();
        moz = 1;

}


} 

function ShowDiv(divid)
{
   if (document.layers) document.layers[divid].visibility="show";
   else document.getElementById(divid).style.visibility="visible";
}

function HideDiv(divid)
{
   if (document.layers) document.layers[divid].visibility="hide";
   else document.getElementById(divid).style.visibility="hidden";
}

function show_description_form()
{

ShowDiv("description_form");
}

function evaluate_description_form(plane_id,description)
{
document.forms['description_form'].plane_id.value = plane_id;
document.forms['description_form'].description.value = description;
}

function show_plane_description(plane_id,description)
{
show_description_form();
evaluate_description_form(plane_id,description);
}

function hide_description_form()
{

HideDiv("description_form");

}


function get_plane(plane_id)
    {


  Initialize_dc(); 
    var start = new Date(); 
    var tm=start.getTime();
    var url=appserver_url + "toolservice?id="+plane_id+'&tm='+tm;
    url = encodeURI(url);
    if(req!=null)
    {
        req.onreadystatechange = Process_plane_request;
        req.open("GET", url, true);
        req.send(null);

    }


    }
 
 function Process_plane_request()
{
  var x;

    if (req.readyState == 4)
        {
        

            if (req.status == 200)
            {
                if(req.responseText=="")
                { x = 1 ; }
                else
                {   

                    if (moz == 1)
                    {
                    var plane = JSON.parse(req.responseText);
                    }
                    else
                    {
                    // IE-ga JSON.parse ei toota.
                  
                    // var plane = eval(req.responseText);
                     //  alert(req.responseText);
                    var plane = JSON.parse(req.responseText);
                    }
                    var plane_id = plane.id;
                    var description = plane.description;
                    show_plane_description(plane_id,description);
                    
                   
                }
            }
            else
            {
                document.getElementById("ajax_response").innerHTML=
                 "Oli mingi probleem andmete saamisega:<br>"+req.statusText;
            }
        }


}