package br.com.tabela.tela;

/**
 *
 * @author Ricardo Borba de Oliveira
 */
import br.com.tabela.codigoEspecifico.limiteNumeros;
import br.com.tabela.codigoEspecifico.soNumeros;
import java.sql.*;
import br.com.tabela.dal.ConexaoBanco;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCadastrar extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    final JPanel aviso = new JPanel();

    public TelaCadastrar() {
        initComponents();
        //inserção txt apenas numeros
        txtId.setDocument(new soNumeros());
        txtPlacar.setDocument(new soNumeros());
        txtMinTemp.setDocument(new soNumeros());
        txtMaxTemp.setDocument(new soNumeros());
        txtRecordeMin.setDocument(new soNumeros());
        txtRecordeMax.setDocument(new soNumeros());
        //inserção txt apenas 3 digitos       
        txtPlacar.setDocument(new limiteNumeros(3));
        txtMinTemp.setDocument(new limiteNumeros(3));
        txtMaxTemp.setDocument(new limiteNumeros(3));
        txtRecordeMin.setDocument(new limiteNumeros(3));
        txtRecordeMax.setDocument(new limiteNumeros(3));

        conexao = ConexaoBanco.conector();
    }

    private void consultar() {
        String sql = "select * from tbresultado where idjogo=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPlacar.setText(rs.getString(2));
                txtMinTemp.setText(rs.getString(3));
                txtMaxTemp.setText(rs.getString(4));
                txtRecordeMin.setText(rs.getString(5));
                txtRecordeMax.setText(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(aviso, "Jogo não existe! Insira um id existente", "Atenção", JOptionPane.WARNING_MESSAGE);
                //limpar os campos
                txtPlacar.setText(null);
                txtMinTemp.setText(null);
                txtMaxTemp.setText(null);
                txtRecordeMin.setText(null);
                txtRecordeMax.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert into tbresultado(idjogo, placar, mintemporada,maxtemporada,recordemin,recordemax) values(?,?,?,?,?,?) ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtPlacar.getText());
            pst.setString(3, txtMinTemp.getText());
            pst.setString(4, txtMaxTemp.getText());
            pst.setString(5, txtRecordeMin.getText());
            pst.setString(6, txtRecordeMax.getText());

            int placar = Integer.parseInt(txtPlacar.getText());
            int minTemp = Integer.parseInt(txtMinTemp.getText());
            int maxTemp = Integer.parseInt(txtMaxTemp.getText());
            int recMin = Integer.parseInt(txtRecordeMin.getText());
            int recMax = Integer.parseInt(txtRecordeMax.getText());

            //validação para salvar os campos obrigatórios e pares
            if (placar / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Placar precisa ser número positivo!");
            } else if (minTemp / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Min Temporada precisa ser número positivo!");
            } else if (maxTemp / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Max Temporada precisa ser número positivo!");
            } else if (recMin / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Recorde Min precisa ser número positivo!");
            } else if (recMax / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Recorde Max precisa ser número positivo!");

            } else if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o ID para continuar");
                txtId.requestFocus();
            } else if (txtPlacar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o  PLACAR para continuar");
                txtPlacar.requestFocus();
            } else if (txtMinTemp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o MAX TEMPORADA para continuar");
                txtMinTemp.requestFocus();
            } else if (txtMaxTemp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o MIN TEMPORADA para continuar");
                txtMaxTemp.requestFocus();
            } else if (txtRecordeMin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o RECORDE MIN para continuar");
                txtRecordeMin.requestFocus();
            } else if (txtRecordeMax.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o RECORDE MAX para continuar");
                txtRecordeMax.requestFocus();

            } else {

                //Confirma e atualiza a inserção dos dados da tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Jogo adicionado com sucesso!");
                    txtPlacar.setText(null);
                    txtMinTemp.setText(null);
                    txtMaxTemp.setText(null);
                    txtRecordeMin.setText(null);
                    txtRecordeMax.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(aviso, "Erro ao salvar! Id ja existe no banco digite outro.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void alterar() {
        String sql = "update tbresultado set placar=?,mintemporada=?,maxtemporada=?,recordemin=?,recordemax=? where idjogo=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPlacar.getText());
            pst.setString(2, txtMinTemp.getText());
            pst.setString(3, txtMaxTemp.getText());
            pst.setString(4, txtRecordeMin.getText());
            pst.setString(5, txtRecordeMax.getText());
            pst.setString(6, txtId.getText());

            //validação da alteração campos obrigatórios e pares
            
            /*if ((txtId.getText().isEmpty())
                    || (txtPlacar.getText().isEmpty())
                    || (txtMinTemp.getText().isEmpty())
                    || (txtMaxTemp.getText().isEmpty())
                    || (txtRecordeMin.getText().isEmpty())
                    || (txtRecordeMax.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "AVISO! Preencha todos os campos para alteração dos dados!");

            }*/
            int placar = Integer.parseInt(txtPlacar.getText());
            int minTemp = Integer.parseInt(txtMinTemp.getText());
            int maxTemp = Integer.parseInt(txtMaxTemp.getText());
            int recMin = Integer.parseInt(txtRecordeMin.getText());
            int recMax = Integer.parseInt(txtRecordeMax.getText());

            //validação dos campos obrigatórios e pares
            if (placar / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Placar precisa ser número positivo!");
            } else if (minTemp / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Min Temporada precisa ser número positivo!");
            } else if (maxTemp / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Max Temporada precisa ser número positivo!");
            } else if (recMin / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Recorde Min precisa ser número positivo!");
            } else if (recMax / 2 == 0) {
                JOptionPane.showMessageDialog(null, "Recorde Max precisa ser número positivo!");

            } else if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o ID para continuar");
                txtId.requestFocus();
            } else if (txtPlacar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o  PLACAR para continuar");
                txtPlacar.requestFocus();
            } else if (txtMinTemp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o MAX TEMPORADA para continuar");
                txtMinTemp.requestFocus();
            } else if (txtMaxTemp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o MIN TEMPORADA para continuar");
                txtMaxTemp.requestFocus();
            } else if (txtRecordeMin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o RECORDE MIN para continuar");
                txtRecordeMin.requestFocus();
            } else if (txtRecordeMax.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o RECORDE MAX para continuar");
                txtRecordeMax.requestFocus();

            }else {

                //Confirma e atualiza a inserção dos dados da tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Jogo adicionado com sucesso!");
                    txtPlacar.setText(null);
                    txtMinTemp.setText(null);
                    txtMaxTemp.setText(null);
                    txtRecordeMin.setText(null);
                    txtRecordeMax.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo Vazio");
        }
    }

    private void remover() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este jogo", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbresultado where idjogo=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int delete = pst.executeUpdate();
                if (delete > 0) {
                    JOptionPane.showMessageDialog(null, "Jogo removido com sucesso!");
                    txtPlacar.setText(null);
                    txtMinTemp.setText(null);
                    txtMaxTemp.setText(null);
                    txtRecordeMin.setText(null);
                    txtRecordeMax.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtPlacar = new javax.swing.JTextField();
        txtMinTemp = new javax.swing.JTextField();
        txtMaxTemp = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRecordeMin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtRecordeMax = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Jogos");

        btnUpdate.setText("Alterar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Deletar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId.setText("Id");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Placar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Min Temporada");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Max Temporada");

        btnSearch.setText("Consultar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnNew.setText("Adicionar");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Todos os campos são obrigatórios");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Recorde Max");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Recorde Min");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPlacar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRecordeMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtMinTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRecordeMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRecordeMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRecordeMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnNew)
                    .addComponent(btnDelete))
                .addGap(30, 30, 30))
        );

        setSize(new java.awt.Dimension(358, 361));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        remover();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        consultar();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        adicionar();
    }//GEN-LAST:event_btnNewActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaxTemp;
    private javax.swing.JTextField txtMinTemp;
    private javax.swing.JTextField txtPlacar;
    private javax.swing.JTextField txtRecordeMax;
    private javax.swing.JTextField txtRecordeMin;
    // End of variables declaration//GEN-END:variables
}
