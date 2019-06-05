package com.redpic.views;

import java.awt.Image;
import RedPic.Remote;
import RedPic.RedPicClient;
import RedPic.RemoteHelper;
import com.redpic.dropbox.DropBox;
import com.redpic.services.CorbaClient;
import com.redpic.utils.SelectorImage;
import com.redpic.utils.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class DashboardForm extends javax.swing.JFrame {

    private User user_account;

    private SelectorImage selector;
    private File selectedImageFile;
    private DropBox db;

    private ORB orb;
    private POA rootPOA;
    private RedPicClient client;

    private Remote remote;

    public DashboardForm(User user) {
        initComponents();
        setLocationRelativeTo(null);
        user_account = user;
        selector = new SelectorImage();
        db = new DropBox();
        initCorbaClient();
        System.out.println("iniciado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelTittle = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        btnShare = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();
        lblimage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTittle.setBackground(new java.awt.Color(237, 44, 12));

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RedPictures");

        btnMinimize.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnMinimize.setForeground(new java.awt.Color(255, 255, 255));
        btnMinimize.setText("-");
        btnMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTittleLayout = new javax.swing.GroupLayout(panelTittle);
        panelTittle.setLayout(panelTittleLayout);
        panelTittleLayout.setHorizontalGroup(
            panelTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTittleLayout.createSequentialGroup()
                .addContainerGap(575, Short.MAX_VALUE)
                .addComponent(btnMinimize)
                .addGap(18, 18, 18)
                .addComponent(btnClose)
                .addGap(21, 21, 21))
            .addGroup(panelTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTittleLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jLabel2)
                    .addContainerGap(468, Short.MAX_VALUE)))
        );
        panelTittleLayout.setVerticalGroup(
            panelTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTittleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnClose))
                .addContainerGap())
            .addGroup(panelTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTittleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        background.add(panelTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 60));

        panelContent.setBackground(new java.awt.Color(255, 255, 255));

        btnShare.setBackground(new java.awt.Color(34, 167, 240));
        btnShare.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnShare.setForeground(new java.awt.Color(255, 255, 255));
        btnShare.setText("Share image");
        btnShare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShareActionPerformed(evt);
            }
        });

        btnUpload.setBackground(new java.awt.Color(242, 38, 19));
        btnUpload.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpload.setForeground(new java.awt.Color(255, 255, 255));
        btnUpload.setText("Upload image");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        lblimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btnUpload)
                .addGap(79, 79, 79)
                .addComponent(btnShare, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimage, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShare, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        background.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 530, 420));

        getContentPane().add(background, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        remote.removeClient(client);
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        selector.setDialogTitle("Selecciona una images");
        int flag = selector.showOpenDialog(this);
        if (flag == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = selector.getSelectedFile();
            try {
                setImageInLabel(selectedImageFile);
            } catch (IOException ex) {
            }
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShareActionPerformed
          if(selectedImageFile != null){
              remote.uploadImg(selectedImageFile.getPath());
          }
//        if (selectedImageFile != null) {
//            if (db.uploadImage(selectedImageFile)) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        
//                        remote.uploadImage(selectedImageFile.getName(), user_account.getEmail());
//                    }
//                }).start();
//                JOptionPane.showMessageDialog(this, "Se ha subido la imagen correctamente", "Aceptado", JOptionPane.INFORMATION_MESSAGE);
//
//            } else {
//                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al subir la imagen", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "No has seleccionado ninguna images", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_btnShareActionPerformed

    private void setImageInLabel(File iamgeFile) throws IOException {
        BufferedImage bi = ImageIO.read(iamgeFile);
        ImageIcon icon = new ImageIcon(bi.getScaledInstance(lblimage.getWidth(),
                lblimage.getHeight(),
                Image.SCALE_DEFAULT));
        lblimage.setIcon(icon);

    }

    private void initCorbaClient() {
        try {
            String refFile = "remote.ref";
            orb = ORB.init(new String[]{"-ORBInitialPort 900"}, null);
            rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            File f = new File(refFile);
            if(!f.exists()){
                System.out.println("remote.ref doesnt exists");
                System.exit(0);
            }
            org.omg.CORBA.Object obj = getRef(refFile);
            remote = RemoteHelper.narrow(obj);
         
            /*
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            remote = (Remote) RemoteHelper.narrow(ncRef.resolve_str("ABC"));
            */
            CorbaClient rp_impl = new CorbaClient(user_account);
            client = rp_impl._this(orb);
            rootPOA.the_POAManager().activate();
            //businessLogic(remote, rp_c);
            remote.addClient(client);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    orb.run();
                }
            }).start();
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }


    
    private org.omg.CORBA.Object getRef(String refFile) throws FileNotFoundException {
        String ref = null;
        try {
            Scanner reader = new Scanner(new File(refFile));
            ref = reader.nextLine();
            reader.close();
        } catch (IOException ex) {
            System.out.println("File error : " + ex.getMessage());
            System.exit(4);
        }
        org.omg.CORBA.Object obj = orb.string_to_object(ref);
        if (obj == null) {
            System.out.println("Invalid IOR");
            System.exit(4);
        }
        
        return obj;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnMinimize;
    private javax.swing.JButton btnShare;
    private javax.swing.JButton btnUpload;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblimage;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelTittle;
    // End of variables declaration//GEN-END:variables
}
