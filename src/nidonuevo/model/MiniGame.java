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
    protected ArrayList<String> options;
    protected ArrayList<Button> buttons;
    private Selector sel;
    private final int spaceY=100;
    private final int spaceX=300;
    private String title="Nido Nuevo, Amigos Nuevos!";
    private Font fntT;
    private final int fontSizeT=40;
    private BufferedImage background;
    private Engine eng;
    private final int x=120;    
    private final int y=500;
    private final int  widthB=250; //buttton width
    private final int  heightB=70; //button height
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
        fntT =new Font("Comic Sans MS",Font.BOLD,fontSizeT);
        options.add("A");        
        options.add("B");
        options.add("C");        
        options.add("SALIR");
        sel=new Selector(x-widthB,y,widthB,heightB,spaceY,spaceX,2,2,"/img/selector_1.png");
        buttons.add(new Button(options.get(0),x,y,widthB,heightB));
        buttons.add(new Button(options.get(1),x,y+spaceY,widthB,heightB));
        buttons.add(new Button(options.get(2),x+spaceX,y,widthB,heightB));
        buttons.add(new Button(options.get(3),x+spaceX,y+spaceY,widthB,heightB));
        
        this.eng=eng;
        background=ImageLoader.loadImage("/img/bg_battle.png");
        
    }
    
    public boolean ordenPop(){
        //arreglar
        
        
        if (eng.getKeyManager().enter){
            
//            if (sel.getOpt()==2 && sel.getOptX()==2 ){
//                System.out.println("2");
//                return true; //eng.getSM().pop();
//            }
            if (sel.getOpt()==2 && sel.getOptX()==2 ){
                System.out.println("2");
                return true; //eng.getSM().pop();
            }
            
        }
        if (eng.getKeyManager().q){
            System.exit(1);
        }
       
        return (false || force);
    }

     public void render(Graphics g){
        
        g.drawImage(background,0,0,800,700,null);
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).render(g);
          
        }
        sel.render(g); 
//        g.drawRect(0, 0, 700, 100);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 100);
        g.setColor(Color.BLACK);
        g.fillRect(0, 101, 800, 5);
        
        g.setColor(Color.BLUE);
        g.setFont(fnt0);
        //lineas
        if (!messages.isEmpty() && cont<messages.size()){
            System.out.println(messages.get(cont));
            
            
            
            String[] auxS=new String[10];
            auxS=messages.get(cont).split(",");
            for (int i=0;i<auxS.length;i++){
                 g.drawString(auxS[i],100,50+i*(35));  
            }
           // g.drawString(messages.get(cont),100,50);   
           // 
        }
       if (!answers.isEmpty() && cont<answers.size()){
           g.setFont(fnt0);
           g.setColor(Color.BLACK);
           int spa=50;
           String[] letters={"A","B","C"};
           for (int i =0; i<answers.get(cont).length;i++){
               g.drawString(letters[i]
                       +". "+answers.get(cont)[i],150,110+spa*(i+1));
           }            
           
       }  
       if (resultado!=null){
           g.drawString(resultado, 500, 300);
       }
    }
     private void nextTurn(){
         if (turno<persons.size()-1)
         turno++;
         else
         turno=0;
     }
    public void tick(){
       
        if (eng.getKeyManager().enter){
            if (aux==0) aux++;
        }
        if (eng.getKeyManager().enterR){
            if (aux==1) aux++;
        }
        
        if (aux==2 && cont<messages.size() && auxI==0){
            eng.getKeyManager().enterR=false;
            eng.getKeyManager().enter=false;
            if (sel.getOpt()==1 && sel.getOptX()==1){ //A
                
                if (correct.get(cont)==1) {
                    int tot=getTotal().get(turno);
                    tot+=points.get(cont);
                    getTotal().set(turno, tot);
                    
                    resultado="Correcto:"+getTotal().get(turno);
                 }else{
                    resultado="Mal:"+getTotal().get(turno);
                }

            }else if (sel.getOpt()==2 && sel.getOptX()==1){ //B
                if (correct.get(cont)==2) {
                    int tot=getTotal().get(turno);
                    tot+=points.get(cont);
                    getTotal().set(turno, tot);
                    resultado="Correcto:"+getTotal().get(turno);
                 }else{
                    resultado="Mal:"+getTotal().get(turno);
                }

            }if (sel.getOpt()==1 && sel.getOptX()==2){//C
                
                if (correct.get(cont)==3) {
                    int tot=getTotal().get(turno);
                    tot+=points.get(cont);
                    getTotal().set(turno, tot);
                    resultado="Correcto:"+getTotal().get(turno);
                 }else{
                    resultado="Mal:"+getTotal().get(turno);
                }

            }
            aux=0;
                
            nextTurn();
           auxI=1;
            
        }else if(cont==messages.size()){
            force=true;
        }else if(aux==2 && auxI==1){
            auxI=0;
            aux=0;
            resultado=null;
            cont++;
        }
        
        if (eng.getKeyManager().down){
            sel.down();
           // sel.print();
        }
        if(eng.getKeyManager().up){
            sel.up();
           // sel.print();
        }
        if(eng.getKeyManager().left){
            sel.left();
           // sel.print();
        }
        if(eng.getKeyManager().right){
            sel.right();
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


        
}
