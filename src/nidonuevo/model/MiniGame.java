/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class MiniGame extends State {
    private int auxI=0;
    private int aux =0;
    private ArrayList<String> messages=new ArrayList<String>();
    private ArrayList<String> options;
    private ArrayList<Button> buttons;
    private Selector sel;
    private int spaceY=100;
    private int spaceX=300;
    private String title="Nido Nuevo, Amigos Nuevos!";
    private Font fntT;
    private int fontSizeT=40;
    private BufferedImage background;
    private Engine eng;
    private int x=120;    
    private int y=500;
    private int  widthB=250; //buttton width
    private int  heightB=70; //button height
    private Font fnt0;
    private Font fnt1;
    private int selectY=y; 
    private int turno =0;
    private ArrayList<Person> persons;
    private ArrayList<Integer> total;
    private ArrayList<Integer> points;
    private ArrayList<String[]> answers;
    private ArrayList<Integer> correct;
    private int cont=0;
    private boolean force=false;
    private String resultado=null;
    public int indice=0;
    public MiniGame(Engine eng,ArrayList<Person> persons, ArrayList<String> messages,ArrayList<String[]> answers, ArrayList<Integer> correct,ArrayList<Integer> points){
        this.answers=answers;
        this.messages=messages;
        this.points=points;
        this.persons=persons;
        total=new ArrayList<Integer>();

        for (int i =0;i<persons.size();i++){
            total.add(0);
       }
        this.correct=correct;
        fnt0 = new Font("Monotype Corsiva",Font.BOLD,50);
        fnt1= new Font("Arial",Font.BOLD,50);
        buttons=new ArrayList<Button>();        
        options=new ArrayList<String>();
        fntT =new Font("Comic Sans MS",Font.BOLD, getFontSizeT());
        options.add("A");        
        options.add("B");
        options.add("C");        
//        options.add("SALIR");
        sel=new Selector(getX()-getWidthB(), getY(), getWidthB(), getHeightB(), getSpaceY(), getSpaceX(),2,2,"/img/selector_1.png");
        buttons.add(new Button(getOptions().get(0), getX(), getY(), getWidthB(), getHeightB()));
        buttons.add(new Button(getOptions().get(1), getX(),getY()+getSpaceY(), getWidthB(), getHeightB()));
        buttons.add(new Button(getOptions().get(2),getX()+getSpaceX(), getY(), getWidthB(), getHeightB()));
//        buttons.add(new Button(getOptions().get(3),getX()+getSpaceX(),getY()+getSpaceY(), getWidthB(), getHeightB()));
        
        this.eng=eng;
        background=ImageLoader.loadImage("/img/bg_battle.png");
        
    }
    
    public boolean ordenPop(){
        //arreglar
        
        
//        if (getEng().getKeyManager().enter){
//            
////            if (sel.getOpt()==2 && sel.getOptX()==2 ){
////                System.out.println("2");
////                return true; //eng.getSM().pop();
////            }
//            if (getSel().getOpt()==2 && getSel().getOptX()==2 ){
//                System.out.println("2");
//                
//                return true; //eng.getSM().pop();
//            }
//            
//        }
        if (getEng().getKeyManager().q){
            System.exit(1);
        }
       if(isForce()){
            //System.out.println("Si");  
            cont=0;
            eng.LMS.getPlayer().getFriends().get(indice).setDibujable(false);
            System.out.println("indice ptm: "+ indice);
            eng.LMS.getPlayer().getCorrectos().set(indice, false);
       }
        return (false || isForce());
    }

     public void render(Graphics g){
        
        g.drawImage(getBackground(),0,0,800,700,null);
        for (int i=0;i<getButtons().size();i++){
            getButtons().get(i).render(g);
          
        }
        getSel().render(g); 
//        g.drawRect(0, 0, 700, 100);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 100);
        g.setColor(Color.BLACK);
        g.fillRect(0, 101, 800, 5);
        
        g.setColor(Color.BLUE);
        g.setFont(getFnt0());
        //lineas
        if (!messages.isEmpty() && getCont()<getMessages().size()){
            System.out.println(getMessages().get(getCont()));
            
            
            
            String[] auxS=new String[10];
            auxS=getMessages().get(getCont()).split(",");
            for (int i=0;i<auxS.length;i++){
                 g.drawString(auxS[i],100,50+i*(35));  
            }
           // g.drawString(messages.get(cont),100,50);   
           // 
        }
       if (!answers.isEmpty() && getCont()<getAnswers().size()){
           g.setFont(getFnt0());
           g.setColor(Color.BLACK);
           int spa=50;
           String[] letters={"A","B","C"};
           for (int i =0; i<getAnswers().get(getCont()).length;i++){
               g.drawString(letters[i]
                       +". "+getAnswers().get(getCont())[i],150,110+spa*(i+1));
           }            
           
       }  
       if (getResultado()!=null){
           g.drawString(getResultado(), 500, 300);
       }
    }
     private void nextTurn(){
         if (getTurno()<getPersons().size()-1)
         setTurno(getTurno() + 1);
         else
         setTurno(0);
     }
    public void tick(){
       
        if (getEng().getKeyManager().enter){
            if (getAux()==0) setAux(getAux() + 1);
        }
        if (getEng().getKeyManager().enterR){
            if (getAux()==1) setAux(getAux() + 1);
        }
        
        if (getAux()==2 && getCont()<getMessages().size() && getAuxI()==0){
            eng.getKeyManager().enterR=false;
            eng.getKeyManager().enter=false;
            if (getSel().getOpt()==1 && getSel().getOptX()==1){ //A
                
                if (getCorrect().get(getCont())==1) {
                    int tot=getTotal().get(getTurno());
                    tot+=getPoints().get(getCont());
                    getTotal().set(getTurno(), tot);
                    
                    setResultado("Correcto:" + getTotal().get(getTurno()));
                 }else{
                    setResultado("Mal:" + getTotal().get(getTurno()));
                }

            }else if (getSel().getOpt()==2 && getSel().getOptX()==1){ //B
                if (getCorrect().get(getCont())==2) {
                    int tot=getTotal().get(getTurno());
                    tot+=getPoints().get(getCont());
                    getTotal().set(getTurno(), tot);
                    
                    setResultado("Correcto:" + getTotal().get(getTurno()));
                 }else{
                    setResultado("Mal:" + getTotal().get(getTurno()));
                }

            }if (getSel().getOpt()==1 && getSel().getOptX()==2){//C
                
                if (getCorrect().get(getCont())==3) {
                    int tot=getTotal().get(getTurno());
                    tot+=getPoints().get(getCont());
                    getTotal().set(getTurno(), tot);
                    setResultado("Correcto:" + getTotal().get(getTurno()));
                 }else{
                    setResultado("Mal:" + getTotal().get(getTurno()));
                }

            }
            setAux(0);
                
            nextTurn();
            setAuxI(1);
            
        }else if(getCont()==getMessages().size()){
            setForce(true);
            
        }else if(getAux()==2 && getAuxI()==1){
            setAuxI(0);
            setAux(0);
            setResultado(null);
            setCont(getCont() + 1);
        }
        
        if (getEng().getKeyManager().down){
            getSel().down();
           // sel.print();
        }
        if(getEng().getKeyManager().up){
            getSel().up();
           // sel.print();
        }
        if(getEng().getKeyManager().left){
            getSel().left();
           // sel.print();
        }
        if(getEng().getKeyManager().right){
            getSel().right();
           // sel.print();
        }   
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        

    /**
     * @return the total
     */
    public ArrayList<Integer> getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(ArrayList<Integer> total) {
        this.total = total;
    }

    /**
     * @return the auxI
     */
    public int getAuxI() {
        return auxI;
    }

    /**
     * @param auxI the auxI to set
     */
    public void setAuxI(int auxI) {
        this.auxI = auxI;
    }

    /**
     * @return the aux
     */
    public int getAux() {
        return aux;
    }

    /**
     * @param aux the aux to set
     */
    public void setAux(int aux) {
        this.aux = aux;
    }

    /**
     * @return the messages
     */
    public ArrayList<String> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    /**
     * @return the options
     */
    public ArrayList<String> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    /**
     * @return the buttons
     */
    public ArrayList<Button> getButtons() {
        return buttons;
    }

    /**
     * @param buttons the buttons to set
     */
    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    /**
     * @return the sel
     */
    public Selector getSel() {
        return sel;
    }

    /**
     * @param sel the sel to set
     */
    public void setSel(Selector sel) {
        this.sel = sel;
    }

    /**
     * @return the spaceY
     */
    public int getSpaceY() {
        return spaceY;
    }

    /**
     * @param spaceY the spaceY to set
     */
    public void setSpaceY(int spaceY) {
        this.spaceY = spaceY;
    }

    /**
     * @return the spaceX
     */
    public int getSpaceX() {
        return spaceX;
    }

    /**
     * @param spaceX the spaceX to set
     */
    public void setSpaceX(int spaceX) {
        this.spaceX = spaceX;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the fntT
     */
    public Font getFntT() {
        return fntT;
    }

    /**
     * @param fntT the fntT to set
     */
    public void setFntT(Font fntT) {
        this.fntT = fntT;
    }

    /**
     * @return the fontSizeT
     */
    public int getFontSizeT() {
        return fontSizeT;
    }

    /**
     * @param fontSizeT the fontSizeT to set
     */
    public void setFontSizeT(int fontSizeT) {
        this.fontSizeT = fontSizeT;
    }

    /**
     * @return the background
     */
    public BufferedImage getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    /**
     * @return the eng
     */
    public Engine getEng() {
        return eng;
    }

    /**
     * @param eng the eng to set
     */
    public void setEng(Engine eng) {
        this.eng = eng;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the widthB
     */
    public int getWidthB() {
        return widthB;
    }

    /**
     * @param widthB the widthB to set
     */
    public void setWidthB(int widthB) {
        this.widthB = widthB;
    }

    /**
     * @return the heightB
     */
    public int getHeightB() {
        return heightB;
    }

    /**
     * @param heightB the heightB to set
     */
    public void setHeightB(int heightB) {
        this.heightB = heightB;
    }

    /**
     * @return the fnt0
     */
    public Font getFnt0() {
        return fnt0;
    }

    /**
     * @param fnt0 the fnt0 to set
     */
    public void setFnt0(Font fnt0) {
        this.fnt0 = fnt0;
    }

    /**
     * @return the fnt1
     */
    public Font getFnt1() {
        return fnt1;
    }

    /**
     * @param fnt1 the fnt1 to set
     */
    public void setFnt1(Font fnt1) {
        this.fnt1 = fnt1;
    }

    /**
     * @return the selectY
     */
    public int getSelectY() {
        return selectY;
    }

    /**
     * @param selectY the selectY to set
     */
    public void setSelectY(int selectY) {
        this.selectY = selectY;
    }

    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     * @return the persons
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * @return the points
     */
    public ArrayList<Integer> getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(ArrayList<Integer> points) {
        this.points = points;
    }

    /**
     * @return the answers
     */
    public ArrayList<String[]> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(ArrayList<String[]> answers) {
        this.answers = answers;
    }

    /**
     * @return the correct
     */
    public ArrayList<Integer> getCorrect() {
        return correct;
    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(ArrayList<Integer> correct) {
        this.correct = correct;
    }

    /**
     * @return the cont
     */
    public int getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * @return the force
     */
    public boolean isForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(boolean force) {
        this.force = force;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


        
}
