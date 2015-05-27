package nidonuevo.model;
//study THAT
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
        private boolean[] keysR;
	public boolean up, down, left, right,eme,q,enter,m,s,enterR,mR,i,iR,z,zR;
	
	public KeyManager(){
		keys = new boolean[256];
                keysR = new boolean[256];
	}
	
	public void tick(){
            System.out.println();
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
                enter= keys[KeyEvent.VK_ENTER];
                q= keys[KeyEvent.VK_Q];
                m=keys[KeyEvent.VK_M];
                mR=keysR[KeyEvent.VK_M];
                s=keys[KeyEvent.VK_S];
                enterR=keysR[KeyEvent.VK_ENTER];
                i=keys[KeyEvent.VK_I];
                iR=keysR[KeyEvent.VK_I];
                z=keys[KeyEvent.VK_Z];
                zR=keysR[KeyEvent.VK_Z];
                
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
                keysR[e.getKeyCode()] = false;
                
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
                keysR[e.getKeyCode()] = true;
                
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
        


}