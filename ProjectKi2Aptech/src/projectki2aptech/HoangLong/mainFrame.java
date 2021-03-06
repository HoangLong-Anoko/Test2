package projectki2aptech.HoangLong;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author voiho
 */
public class mainFrame extends javax.swing.JFrame implements KeyListener{
    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
        
        
        initComponents();        
        jPanel1.add(labelSplash);
        jPanel1.add(labelExplode);        
        generatePointCounter();
        generateBird();
        generateSpikes();
        generateBombird();
        generateBombird2();
        jPanel1.add(labelBomb);
        jPanel1.add(labelBomb2);
        generateBackgrounds();
        this.addKeyListener(this);                               
        countPoint.start();
        loopPosition = labelBackgroundReflect.getLocation().x;   
        checkCollider.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("B&B");
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1226, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    // Get key event
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                moveUp = true;
                moveDown = false;
                break;
            case KeyEvent.VK_S:
                moveUp = false;
                moveDown = true;
                break;
            case KeyEvent.VK_D:
                moveRight = true;
                moveLeft = false;
                break;
            case KeyEvent.VK_A:
                moveRight = false;
                moveLeft = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                moveUp = false;
                break;
            case KeyEvent.VK_S:
                moveDown = false;
                break;
            case KeyEvent.VK_D:
                moveRight = false;
                break;
            case KeyEvent.VK_A:
                moveLeft = false;
                break;
            default:
                break;
        }
    }       
   
    // Main bird
    public Thread birdIdleThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!stopGame) {
                bird = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + birdPictures[indexBird]);
                labelBird.setIcon(bird);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }            
                indexBird++;
                if (indexBird > birdPictures.length - 1) {
                    indexBird = 0;
                }
            }
            birdIdleThread.interrupt();
        }
    });
    
    public Thread birdDeadThread = new Thread(new Runnable() {
        @Override
        public void run() {
            bird = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\dead.png");
            labelBird.setIcon(bird);
            birdDeadThread.interrupt();
        }
    });
    
    public Timer movementBird = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (moveUp && labelBird.getLocation().y >= -100 && !stopGame) {
                labelBird.setLocation(labelBird.getLocation().x, labelBird.getLocation().y - birdSpeed);
            }
            if (moveDown && labelBird.getLocation().y <= 500 && !stopGame) {
                labelBird.setLocation(labelBird.getLocation().x, labelBird.getLocation().y + birdSpeed);
            }
            if (moveLeft && labelBird.getLocation().x >= -50 && !stopGame) {
                labelBird.setLocation(labelBird.getLocation().x - birdSpeed, labelBird.getLocation().y);
            }
            if (moveRight && labelBird.getLocation().x <= 1000 && !stopGame) {
                labelBird.setLocation(labelBird.getLocation().x + birdSpeed, labelBird.getLocation().y);
            }            
        }
    });
    
    // Background
    public Thread movingBackground = new Thread(new Runnable() {
        @Override
        public void run() {           
            while (!stopGame) {                
                try {
                    labelBackground.setLocation(labelBackground.getLocation().x - backgroundSpeed, labelBackground.getLocation().y);
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (Math.abs(labelBackground.getLocation().x) >= labelBackground.getSize().width) {
                    labelBackground.setLocation(loopPosition - wrongCalc, labelBackground.getLocation().y);
                    wrongCalc += 2;
                }
            }
            movingBackground.interrupt();
        }
    });
    
    public Thread backgroundAnimThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                background = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + backgroundPictures[indexBackground]);
                labelBackground.setIcon(background);
                try {
                    Thread.sleep(150);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }            
                indexBackground++;
                if (indexBackground > backgroundPictures.length - 1) {
                    indexBackground = 0;                    
                }
            }
        }
    });              // not 
    
    public Thread movingBackgroundReflect = new Thread(new Runnable() {
        @Override
        public void run() {          
            while (!stopGame) {                
                try {
                    labelBackgroundReflect.setLocation(labelBackgroundReflect.getLocation().x - backgroundSpeed, labelBackgroundReflect.getLocation().y);
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (Math.abs(labelBackgroundReflect.getLocation().x) >= labelBackgroundReflect.getSize().width) {
                    labelBackgroundReflect.setLocation(loopPosition - wrongCalc, labelBackgroundReflect.getLocation().y);
                    wrongCalc += 2;
                }
            }
            movingBackgroundReflect.interrupt();
        }
    }); 
    
    public Thread backgroundAnimThreadReflect = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                backgroundReflect = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + backgroundPicturesReflect[indexBackgroundReflect]);
                labelBackgroundReflect.setIcon(backgroundReflect);
                try {
                    Thread.sleep(150);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }            
                indexBackgroundReflect++;
                if (indexBackgroundReflect > backgroundPictures.length - 1) {
                    indexBackgroundReflect = 0;
                }
            }
        }
    });         // not 
    
    // Horizontal spike
    public Thread movingSpikeDown = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!stopGame) {                
                labelSpike.setLocation(labelSpike.getLocation().x - spikeSpeed, labelSpike.getLocation().y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Math.abs(labelSpike.getLocation().x - 1500) >= 1500) {
                    labelSpike.setLocation(1500, 300 + rd.nextInt(200));
                }                 
            }
            movingSpikeDown.interrupt();
        }
    });         
    
    // Bombird
    public Thread bombirdIdleThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                bombird = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + bombirdIdlePictures[indexBombird]);
                labelBombird.setIcon(bombird);                
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }            
                indexBombird++;
                if (indexBombird > bombirdIdlePictures.length - 1) {
                    indexBombird = 0;
                }
            }
        }
    });                     // not       
    
    public Thread movingBombird = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!stopGame) {                
                labelBombird.setLocation(labelBombird.getLocation().x - bombirdSpeed, labelBombird.getLocation().y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Math.abs(labelBombird.getLocation().x - 1500) >= 2000) {
                    labelBombird.setLocation(1500, 0 + rd.nextInt(200));
                }                 
            }
            movingBombird.interrupt();
        }
    });
    
    public Thread throwBomb = new Thread(new Runnable() {
        @Override
        public void run() {
            int startX = labelBombird.getLocation().x;                
            int startY = labelBombird.getLocation().y;
            System.out.println(startX + " " + startY);
            int targetX = labelBird.getLocation().x;
            int targetY = labelBird.getLocation().y;;
            idleBomb(startX, startY);
            
            while (!stopGame) {                                            
                labelBomb.setLocation(labelBomb.getLocation().x + (targetX - startX)/80 , labelBomb.getLocation().y + (targetY - startY)/5);
                //labelBomb.setLocation(labelBomb.getLocation().x + (targetX - startX)/40 , labelBomb.getLocation().y + (targetY - startY));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Math.abs(labelBombird.getLocation().x - labelBomb.getLocation().x) > 1600) {
                    labelBomb.setLocation(labelBombird.getLocation().x, labelBombird.getLocation().y);
                    //targetX = labelBird.getLocation().x;
                    targetY = labelBird.getLocation().y;;
                }
            }
            firstTime = false;            
        }
    });
    
    public Thread bombirdIdleThread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                bombird2 = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + bombirdIdlePictures2[indexBombird2]);
                labelBombird2.setIcon(bombird2);                
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }            
                indexBombird2++;
                if (indexBombird2 > bombirdIdlePictures2.length - 1) {
                    indexBombird2 = 0;
                }
            }
        }
    });                     // not       
    
    public Thread movingBombird2 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!stopGame) {                
                labelBombird2.setLocation(labelBombird2.getLocation().x - bombirdSpeed, labelBombird2.getLocation().y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Math.abs(labelBombird2.getLocation().x - 1500) >= 2000) {
                    labelBombird2.setLocation(1500, 0 + rd.nextInt(200));
                }                 
            }
            movingBombird2.interrupt();
        }
    });
    
    public Thread throwBomb2 = new Thread(new Runnable() {
        @Override
        public void run() {
            int startX = labelBombird2.getLocation().x;                
            int startY = labelBombird2.getLocation().y;
            int targetX = labelBird.getLocation().x;
            int targetY = labelBird.getLocation().y;;
            idleBomb2(startX, startY);
            
            while (!stopGame) {                                            
                labelBomb2.setLocation(labelBomb2.getLocation().x + (targetX - startX)/40 , labelBomb2.getLocation().y + (targetY - startY)/5);
                //labelBomb2.setLocation(labelBomb2.getLocation().x + (targetX - startX)/40 , labelBomb2.getLocation().y + (targetY - startY));
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Math.abs(labelBombird2.getLocation().x - labelBomb2.getLocation().x) > 1600) {
                    labelBomb2.setLocation(labelBombird2.getLocation().x, labelBombird2.getLocation().y);
                    //targetX = labelBird.getLocation().x;
                    targetY = labelBird.getLocation().y;;
                }
            } 
            firstTime = false;
        }
    });
    
    // Others
    public Thread splashScreen = new Thread(new Runnable() {
        @Override
        public void run() {           
            try {
                splash = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\white.jpg");
                labelSplash.setIcon(splash);
                labelSplash.setSize(2000, 2000);
                labelSplash.setLocation(-10, -600);
                Thread.sleep(100);
                labelSplash.setSize(0, 0);
            } catch (InterruptedException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            splashScreen.interrupt();
        }
    }); 
    
    public Thread checkCollider = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!stopGame) {  
                System.out.print("");
                if (boxCollider(labelBird, labelSpike, 10, 150, 50)) {
                    System.out.println("Bị xiên chết");                   
                    stopGame();
                } 
                if (boxCollider(labelBird, labelBombird, 75, 35, 0)) {
                    generateExplode(labelBird.getLocation().x, labelBird.getLocation().y);
                    labelBombird.setVisible(false);
                    System.out.println("Dính bombird 1");
                    stopGame();
                }
                if (boxCollider(labelBird, labelBombird2, 75, 35, 0)) {
                    generateExplode(labelBird.getLocation().x, labelBird.getLocation().y);
                    labelBombird.setVisible(false);
                    System.out.println("Dính bombird 2");
                    stopGame();//ssssssssss
                }
                if (boxCollider(labelBird, labelBomb, 50, 50, 0)) {
                    System.out.println(labelBird.getLocation() + " " + labelBomb.getLocation() + " (1)");
                    generateExplode(labelBomb.getLocation().x, labelBomb.getLocation().y);
                    labelBomb.setVisible(false);
                    System.out.println("Ăn bom chết 1");                   
                    stopGame();
                }
                if (boxCollider(labelBird, labelBomb2, 50, 50, 0)) {
                    System.out.println(labelBird.getLocation() + " " + labelBomb2.getLocation() + " (2)");
                    generateExplode(labelBomb2.getLocation().x, labelBomb2.getLocation().y);
                    labelBomb2.setVisible(false);
                    System.out.println("Ăn bom chết 2");                   
                    stopGame();
                }
            }
            checkCollider.interrupt();
        }
    });
    
    public Thread countPoint = new Thread(new Runnable() {
        @Override
        public void run() {
            while(!stopGame){                
                try {
                    pointCount++;
                    labelPoint.setText(String.valueOf(pointCount));
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            countPoint.interrupt();
        }
    });
    
    public boolean boxCollider(JLabel label1, JLabel label2, int sizeX, int sizeY, int modify){
        if (Math.abs(label1.getLocation().x + modify - label2.getLocation().x) <= sizeX && Math.abs(label1.getLocation().y - label2.getLocation().y) <= sizeY   && label2.getLocation().x != 0) {
            System.out.println(Math.abs(label1.getLocation().x + modify - label2.getLocation().x) + " lol " + Math.abs(label1.getLocation().y - label2.getLocation().y));
            System.out.println(label1.getLocation().x);
            System.out.println(label2.getLocation().x);
            return true;
        }
        else return false;
    }
    
    // Content generator
    public void generateBird(){
        jPanel1.add(labelBird);
        labelBird.setSize(300, 300);
        birdIdleThread.start();
        movementBird.start();
    }
    
    public void generateBombird(){
        jPanel1.add(labelBombird);
        labelBombird.setSize(300, 300);
        labelBombird.setLocation(3400,0);
        bombirdIdleThread.start(); 
        movingBombird.start();
        throwBomb.start();
    }
    
    public void generateBombird2(){
        jPanel1.add(labelBombird2);
        labelBombird2.setSize(300, 300);
        labelBombird2.setLocation(2500,0);
        bombirdIdleThread2.start(); 
        movingBombird2.start();
        throwBomb2.start();
    }
    
    public void generateBackgrounds(){
        jPanel1.add(labelBackground);
        labelBackground.setSize(1778, 800);
        jPanel1.add(labelBackgroundReflect);
        labelBackgroundReflect.setSize(1778, 800);
        labelBackgroundReflect.setLocation(labelBackgroundReflect.getLocation().x + labelBackground.getSize().width, labelBackgroundReflect.getLocation().y);
        backgroundAnimThread.start();
        backgroundAnimThreadReflect.start();
        movingBackground.start();
        movingBackgroundReflect.start();
    }
    
    public void generateSpikes(){
        jPanel1.add(labelSpike);
        labelSpike.setSize(50, 500);
        spike = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\spike.png");
        labelSpike.setIcon(spike);
        labelSpike.setLocation(1500, 300 + rd.nextInt(200));
        movingSpikeDown.start();
    }
    
    public void generatePointCounter(){
        jPanel1.add(labelPoint);
        labelPoint.setLocation(600, -50);
        labelPoint.setSize(300, 300);
        labelPoint.setFont(new Font("Arial", Font.BOLD, 75));
        labelPoint.setForeground(Color.WHITE);
    }
    
    public void generateExplode(int x, int y){
        Thread clone = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            indexExplode = 0;            
            while (indexExplode <= 4) {
                try {                    
                    explode = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + explodePictures[indexExplode]);
                    labelExplode.setIcon(explode); 
                    labelExplode.setSize(500, 300);
                    labelExplode.setLocation(x,y);               
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                indexExplode++;           
            }
            Thread.currentThread().interrupt();
        }); 
        clone.start();
    }
    
    public void idleBomb(int x, int y){
        boolean throwBomb = true;
        labelBomb.setSize(500, 300);
        labelBomb.setLocation(x, y);
        System.out.println("setlocation " + x + " " + y);
        Thread clone = new Thread(() -> {                      
            while (throwBomb) {
                try {                    
                    labelBomb.setIcon(bomb);
                    bomb = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + bombPictures[indexBomb]);                                      
                    Thread.sleep(40);
                } catch (Exception e) {
                    
                }
                indexBomb++;
                if (indexBomb > bombPictures.length) {
                    indexBomb = 0;
                }                
            }
            Thread.currentThread().interrupt();
        }); 
        clone.start();
    }
    
    public void idleBomb2(int x, int y){
        boolean throwBomb = true;
        labelBomb2.setSize(500, 300);
        labelBomb2.setLocation(x, y);
        Thread clone = new Thread(() -> {                      
            while (throwBomb) {
                try {                    
                    labelBomb2.setIcon(bomb);
                    bomb = new ImageIcon(System.getProperty("user.dir") + "\\src\\projectki2aptech\\HoangLong\\picture\\" + bombPictures[indexBomb]);                                      
                    Thread.sleep(40);
                } catch (Exception e) {
                    
                }
                indexBomb++;
                if (indexBomb > bombPictures.length) {
                    indexBomb = 0;
                }                
            }
            Thread.currentThread().interrupt();
        }); 
        clone.start();
    }
    
    public void stopGame(){
        splashScreen.start();
        birdDeadThread.start();
        stopGame = true;
        JOptionPane.showMessageDialog(null, "Thua mẹ r nhé!\n" + pointCount + " điểm.");
        restartApplication();
    }
    
    public void restartApplication(){
        this.dispose();
        new mainFrame().setVisible(true);
        firstTime = true;
        
    }

    Random rd = new Random();
    JLabel labelPoint = new JLabel();
    JLabel labelBird = new JLabel();
    JLabel labelBackground = new JLabel();
    JLabel labelBackgroundReflect = new JLabel();
    JLabel labelSpike = new JLabel();
    JLabel labelBombird= new JLabel();
    JLabel labelBombird2= new JLabel();
    JLabel labelSplash= new JLabel();
    JLabel labelExplode= new JLabel();
    JLabel labelBomb= new JLabel();
    JLabel labelBomb2= new JLabel();
    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveRight = false;
    boolean moveLeft = false;
    boolean stopGame = false;
    int pointCount = 0;
    int birdSpeed = 10;
    int bombirdSpeed = 5;
    int backgroundSpeed = 2;
    int spikeSpeed = 7;
    int boomSpeed = 30;
    ImageIcon bird;
    ImageIcon background;
    ImageIcon backgroundReflect;
    ImageIcon spike;
    ImageIcon bombird;
    ImageIcon bombird2;
    ImageIcon splash;
    ImageIcon explode;
    ImageIcon bomb;
    String [] birdPictures = {"hawk 1.png", "hawk 2.png", "hawk 3.png", "hawk 4.png"};
    String [] backgroundPictures = {"background 1.png", "background 2.png", "background 3.png", "background 4.png"};
    String [] backgroundPicturesReflect = {"background 1 reflect.png", "background 2 reflect.png", "background 3 reflect.png", "background 4 reflect.png"};
    String [] bombirdIdlePictures = {"bombird 1.png", "bombird 2.png"};
    String [] bombirdIdlePictures2 = {"bombird 1.png", "bombird 2.png"};
    String [] explodePictures = {"Explode 1.png", "Explode 2.png", "Explode 3.png", "Explode 4.png", "null"};
    String [] bombPictures = {"bomb 1.png", "bomb 2.png", "bomb 3.png", "bomb 4.png", "bomb 5.png"};
    int indexBird = 0;
    int indexBackground = 0;
    int indexBackgroundReflect = 0;
    int indexBombird = 0;
    int indexBombird2 = 0;
    int indexExplode = 0;
    int indexBomb = 0;
    int loopPosition = 0;
    int wrongCalc = 3;
    boolean firstTime = true;
}
