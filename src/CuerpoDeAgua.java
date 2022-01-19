public class CuerpoDeAgua extends ObjetoGeografico {


    public CuerpoDeAgua(String Nombre,int Id_cuerpo_agua,String Municipio,String T_CuerpoDeAgua, String Tipo_Agua, double IRCA){
        super(Nombre,Id_cuerpo_agua,Municipio,T_CuerpoDeAgua,Tipo_Agua,IRCA);//estos nombres dentro de paramatros son parametros dif a los atributos. por si se ve el nombre igual al atributo

    }
 
    public String nivel(double cuerpo_agua){
        String cuerpo="";
        if((cuerpo_agua)>0 && (cuerpo_agua)<=5){
            cuerpo="SIN RIESGO";
        }else{
            if((cuerpo_agua)>=5.1 && (cuerpo_agua<=14)){
                cuerpo="BAJO";
            }else{
                if((cuerpo_agua)>=14.1 && (cuerpo_agua)<=35){
                    cuerpo="MEDIO";
                }else{
                    if((cuerpo_agua)>=35.1 && (cuerpo_agua)<=80){
                        cuerpo="ALTO";
                    }else{
                        if((cuerpo_agua)>=80.1 && (cuerpo_agua)<=100){
                            cuerpo="INVIABLE SANITARIAMENTE"; 
                        }
                    }

                }
            }
        } 
        return cuerpo;  
    }
}   