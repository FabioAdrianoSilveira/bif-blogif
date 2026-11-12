package bif_backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @ManyToOne(optional = false)
    private Usuario autor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> respostas = new ArrayList<>();

    public void adicionarResposta(Comentario resposta) {
        this.respostas.add(resposta);
    }

    public void removerResposta(Comentario resposta) {
        this.respostas.remove(resposta);
    }
}

