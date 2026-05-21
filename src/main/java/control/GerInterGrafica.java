/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import viewer.DlgCadJogo;
import viewer.DlgCadUser;
import viewer.MainFrame;
import viewer.UserFrame;

/**
 *
 * @author iago_
 */
public class GerInterGrafica {
    private MainFrame janPrinc = null;
    private UserFrame janUsuario = null;
    private DlgCadUser janCadUser = null;
    private DlgCadJogo janCadJogo = null;
    private GerenciadorDominio gerDominio;
    
        // ## SINGLETON ###
    
    private static GerInterGrafica myInstance = new GerInterGrafica();
    
    private GerInterGrafica() {
        try {
            gerDominio = new GerenciadorDominio();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, ex, "Inicializacao", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }           

    public static GerInterGrafica getMyInstance() {
        return myInstance;
    }

    public GerenciadorDominio getGerenciadorDominio() {
        return gerDominio;
    }
    
    // ### FIM do SINGLETON
        // ABRIR JDIALOG
    private JDialog abrirJanela(Frame parent, JDialog dlg, Class classe) {
        if (dlg == null){     
            try {
                dlg = (JDialog) classe.getConstructor(Frame.class, boolean.class).newInstance(parent,true);                                
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                JOptionPane.showMessageDialog(parent, "Erro ao abrir a janela " + classe.getName() + ". " + ex.getMessage() );
            } 
        }               
        dlg.setVisible(true); 
        return dlg;    
    }
    
    public void abrirPrincipal() {
        if ( janPrinc == null) {
            janPrinc = new MainFrame();
        }
        janPrinc.setVisible(true);
    }
    
    public void abrirCadUser() {
        abrirJanela(janPrinc, janCadUser, DlgCadUser.class);
    }
    
    public void abrirCadJogo() {
        abrirJanela(janPrinc, janCadJogo, DlgCadJogo.class);
    }
    
     public void abrirUser() {
        if ( janUsuario == null) {
            janUsuario = new UserFrame();
        }
        janPrinc.setVisible(false);
        janUsuario.setVisible(true);
    }
     
     public void fecharUser() {
        janUsuario.setVisible(false);
    }
        
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            // Logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        // TRADUÇÃO
        javax.swing.UIManager.put("OptionPane.yesButtonText", "Sim"); 
        javax.swing.UIManager.put("OptionPane.noButtonText", "Não");
        javax.swing.UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        
        
        /* Create and display the form */
        GerInterGrafica.getMyInstance().abrirPrincipal();
    }
}
