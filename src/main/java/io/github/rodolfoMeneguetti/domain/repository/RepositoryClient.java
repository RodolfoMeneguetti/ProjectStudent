package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryClient {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) ";

    private static String SELECTALLCLIENTES = "SELECT * FROM CLIENTE";

    @Autowired
    private JdbcTemplate jdbcTamplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTamplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> listarClientes() {
       return jdbcTamplate.query(SELECTALLCLIENTES, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente (id, nome);
            }
        });
    }
}


