/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

/**
 *
 * @author TOSHIBA
 */
public class TriggerChangeMap extends Trigger{
    private int pX;
    private int pY;
    private int changeTo;
    public TriggerChangeMap(int x,int y,int changeTo,int pX,int pY){
        this.x=x;
        this.y=y;
        this.changeTo=changeTo;
        this.pX=pX;
        this.pY=pY;
    }


    @Override
    public void execTrigger(LocalMap aThis) {
        aThis.setMapAct(getChangeTo());
        aThis.getPlayer().positionX=getpX();
        aThis.getPlayer().positionY=getpY();
    }

    /**
     * @return the pX
     */
    public int getpX() {
        return pX;
    }

    /**
     * @param pX the pX to set
     */
    public void setpX(int pX) {
        this.pX = pX;
    }

    /**
     * @return the pY
     */
    public int getpY() {
        return pY;
    }

    /**
     * @param pY the pY to set
     */
    public void setpY(int pY) {
        this.pY = pY;
    }

    /**
     * @return the changeTo
     */
    public int getChangeTo() {
        return changeTo;
    }

    /**
     * @param changeTo the changeTo to set
     */
    public void setChangeTo(int changeTo) {
        this.changeTo = changeTo;
    }
    
}
