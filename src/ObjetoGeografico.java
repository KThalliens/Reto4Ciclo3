public class ObjetoGeografico {
    //Atributos: Instanciar la clase.
    private String Nombre; //el estandar dice que los atributos deben ser privados
    private int Id_cuerpo_agua;
    private String Municipio;
    private String T_CuerpoDeAgua;
    private String Tipo_Agua;
    private double IRCA;


    //Definir los constructores.Se crea por estandarizacion; es bueno crearlo por si acaso
    public ObjetoGeografico(){ // constructor vacio
    }
    //contructor con parametros
    public  ObjetoGeografico(String nombre, int id_agua,String municipio,String T_cuerpodeagua,String Tipo_agua,double irca){ //estos nombres dentro de paramatros son parametros dif a los atributos. por si se ve el nombre igual al atributo
        this.Nombre=nombre;
        this.Id_cuerpo_agua=id_agua;
        this.Municipio=municipio;
        this.T_CuerpoDeAgua=T_cuerpodeagua;
        this.Tipo_Agua=Tipo_agua;
        this.IRCA =irca;
    }
    //constructor con un solo parametro
    public ObjetoGeografico(String Nombre){ 
        this.Nombre=Nombre;
    }
    public ObjetoGeografico(Double IRCA){ 
    this.IRCA=IRCA;
    }

    //Definir el metodo SET por cada atributos o Setear
    //Setear asignar valores a los atributos
    public void setNombre(String Nombre){
        this.Nombre=Nombre;
    }
    public void setId_cuerpo_agua(int id_agua){
        this.Id_cuerpo_agua=id_agua;
    }
    public void setMunicipio(String municipio){
        this.Municipio=municipio;
    }
    public void setT_CuerpoDeAgua(String T_cuerpodeagua){
        this.T_CuerpoDeAgua=T_cuerpodeagua;
    }
    public void setTipo_Agua(String Tipo_agua){
        this.Tipo_Agua=Tipo_agua;
    }
    public void setIRCA(double irca){
        this.IRCA=irca;
    }

    //Metodo GET, para obtener informacion de un abributo.
    public String getNombre(){
        return Nombre;
    }
    public int getId_cuerpo_agua(){
        return Id_cuerpo_agua;
    }
    public String getMunicipio(){
        return Municipio;
    }
    public String getT_CuerpoDeAgua(){
        return T_CuerpoDeAgua;
    }
    public String getTipo_Agua(){
        return Tipo_Agua;
    }
    public Double getIRCA(){
        return IRCA;
    }
    public void add(String nombre2) {
    }
}   