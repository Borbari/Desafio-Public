package br.com.tabela.tela;

import br.com.tabela.codigoEspecifico.limiteNumeros;
import br.com.tabela.codigoEspecifico.soNumeros;
import br.com.tabela.dal.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
//importa biblioteca rs2x pesquisa
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ricardo Borba de Oliveira
 */
public class TelaPontuacao extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaPontuacao
     */
    public TelaPontuacao() {
        initComponents();
        conexao = ConexaoBanco.conector();
        txtPesquisar.setDocument(new soNumeros());
        txtPesquisar.setDocument(new limiteNumeros(3));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();
        lblSoma = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMinTemp = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMaxTemp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tabela Pontuação");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setText("Pesquisar");

        tblResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Placar", "Min Temp", "Max Temp", "Recorde Min", "Recorde Max"
            }
        ));
        tblResultado.setEnabled(false);
        jScrollPane1.setViewportView(tblResultado);

        lblSoma.setText("jLabel2");

        jLabel2.setText("Recorde Quebrado");

        jLabel3.setText("Min Temporada");

        lblMinTemp.setText("jLabel4");

        jLabel5.setText("Max Temporada");

        lblMaxTemp.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMinTemp)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(lblMaxTemp)
                    .addComponent(jLabel2)
                    .addComponent(lblSoma, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMinTemp)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaxTemp)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblSoma)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(698, 389));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisar_jogo();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        min_temp();
        max_temp();
        recorde_quebrado();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(TelaPontuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPontuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPontuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPontuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPontuacao().setVisible(true);
            }
        });
    }

    //metodo para pesquisar jogos
    private void pesquisar_jogo() {
        String sql = "select * from tbresultado where idjogo like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo na caixa de pesquisa
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //usa a biblioteca para autocomplet
            tblResultado.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    private void recorde_quebrado() {
        int valor=0;
        String sql = "select sum(recordemax) as total from tbresultado";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
                valor = rs.getInt("total");
                lblSoma.setText(String.valueOf(valor));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro Na soma" + e);
        }

    }
    private void min_temp() {
        int min_temp=0;
        String sql = "select sum(mintemporada) as total from tbresultado";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
                min_temp = rs.getInt("total");
                lblMinTemp.setText(String.valueOf(min_temp));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro Na soma");
        }

    }
    private void max_temp() {
        int max_temp=0;
        String sql = "select sum(maxtemporada) as total from tbresultado";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
                max_temp = rs.getInt("total");
                lblMaxTemp.setText(String.valueOf(max_temp));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro Na soma" + e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaxTemp;
    private javax.swing.JLabel lblMinTemp;
    private static javax.swing.JLabel lblSoma;
    private javax.swing.JTable tblResultado;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
