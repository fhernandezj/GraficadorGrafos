/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficadorgrafos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author hhlopez
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    
    ArrayList<Nodo> nodos;
    ArrayList<Arco> arcos;
    int cantNodos = 0;
    Nodo nodoinicial = null, nodofinal = null;
    int tamNodos = 30;
    int[][] matriz;
    
    public Ventana() {
        initComponents();
        nodos = new ArrayList<Nodo>();
        arcos = new ArrayList<Arco>();
        Insertar(jPanel1.getGraphics());
    }
    
    private void Insertar(Graphics g){
        jPanel1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jRadioButton1.isSelected()){
                    g.setColor(Color.BLACK);
                    g.fillOval(e.getX() - (tamNodos/2), e.getY() - (tamNodos/2), tamNodos, tamNodos);
                    nodos.add(new Nodo(cantNodos, e.getX()- (tamNodos/2), e.getY()- (tamNodos/2), Color.BLACK));
                    g.setColor(Color.yellow);
                    g.drawString(Integer.toString(cantNodos), e.getX(), e.getY());
                    cantNodos++;
                }else{
                    if(nodoinicial == null){
                        nodoinicial = BuscarNodo(e.getX(), e.getY());
                        if(nodoinicial != null){
                            seleccionarNodo(nodoinicial, g, Color.red);
                        }
                    }else{
                        nodofinal = BuscarNodo(e.getX(), e.getY());
                        if(nodofinal!=null){
                            seleccionarNodo(nodofinal, g, Color.red);
                            if(nodofinal.name != nodoinicial.name){
                                g.setColor(Color.black);
                                g.drawLine(nodoinicial.posx + (tamNodos/2), nodoinicial.posy + (tamNodos/2), 
                                        nodofinal.posx + (tamNodos/2), nodofinal.posy + (tamNodos/2));
                                int distancia = distancia(nodoinicial.posx + (tamNodos/2), 
                                        nodofinal.posx + (tamNodos/2), nodoinicial.posy + (tamNodos/2), nodofinal.posy + (tamNodos/2));
                                arcos.add(new Arco(nodoinicial.name, nodofinal.name, 
                                        nodoinicial.posx + (tamNodos/2), nodoinicial.posy + (tamNodos/2), 
                                        nodofinal.posx + (tamNodos/2), nodofinal.posy + (tamNodos/2), distancia));
                                System.out.println("distancia: " + distancia);
                            }else{
                                seleccionarNodo(nodoinicial, g, Color.black);
                            }
                            seleccionarNodo(nodoinicial, g, Color.black);
                            seleccionarNodo(nodofinal, g, Color.black);
                            nodoinicial = null;
                        }else{
                            seleccionarNodo(nodoinicial, g, Color.black);
                            nodoinicial = null;
                        }
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
 });
    }
    
    private int distancia(int x1, int x2, int y1, int y2){
        double dist = Math.sqrt(Math.pow(x2-x1, 2.0) + Math.pow(y2-y1, 2.0));
        return (int)dist;
    }
    
    private void seleccionarNodo(Nodo nodo, Graphics g, Color color){
        g.setColor(color);
        g.drawOval(nodo.posx, nodo.posy, tamNodos-1, tamNodos-1);
    }
    
    private Nodo BuscarNodo(int coordx, int coordy){
        Nodo nodoReturn = null;
        for (Nodo nodo : nodos) {
            if(coordx >= nodo.posx && coordx <= nodo.posx + tamNodos && 
                    coordy >= nodo.posy && coordy <= nodo.posy + tamNodos){
                nodoReturn = nodo;
                break;
            }
        }
        return nodoReturn;
    }
    
    private void calcularMatriz(){
        matriz = new int[nodos.size()][nodos.size()];
        for (Arco arco : arcos) {
            matriz[arco.nodoinicial][arco.nodofinal] = arco.dist;
            matriz[arco.nodofinal][arco.nodoinicial] = arco.dist;
        }
    }
    
    private void Prim(int[][] matriz){
        boolean vector[]  =new boolean[nodos.size()];
        vector[0] = true;
        while(faltaAlguno(vector)){
            int min = menor(matriz, vector);
            vector[min] = true;
        }
    }
    
    private int menor(int[][] matriz, boolean[] vector){
        int menor = Integer.MAX_VALUE;
        int fila = -1, col=-1;
        for (int i = 0; i < matriz.length; i++) {
            if(vector[i]){
                for (int j = 0; j < matriz.length; j++) {
                    if(matriz[j][i]!=0 && vector[j]==false && matriz[j][i]<=menor){
                        menor = matriz[j][i];
                        fila = j;
                        col = i;
                    }
                }
            }
        }
        System.out.println("" + fila + " - " + col);
        return fila;
    }
    
    private boolean faltaAlguno(boolean[] vector){
        for (int i = 0; i < vector.length; i++) {
            if(!vector[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton1.setText("Nodo");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton2.setText("Arco");

        jButton1.setText("Algoritmo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 357, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        calcularMatriz();
        Prim(matriz);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
