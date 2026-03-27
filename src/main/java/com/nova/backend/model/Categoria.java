package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Entità per la categorizzazione del catalogo.
 * Utilizza una struttura ad albero (Adjacency List) tramite associazione auto-referenziale su parent_id,
 * permettendo N livelli gerarchici di annidamento.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Riferimento alla categoria superiore (se null, è categoria radice/root)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Categoria categoriaPadre;

    @Column(nullable = false, length = 100)
    private String nome;

    // Identificativo univoco ottimizzato per stringhe negli URL (es: "smart-home-kit")
    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    // Sotto-rami gerarchici collegati a questa radice
    @OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> sottocategorie;

    // Lista dei prodotti appartenenti a questo preciso ramo della categoria
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Prodotto> prodotti;

    public Categoria() {}

    public Categoria(Categoria categoriaPadre, String nome, String slug) {
        this.categoriaPadre = categoriaPadre;
        this.nome = nome;
        this.slug = slug;
    }

    // Valutazione uguaglianza tramite l'identificativo tecnico e url slug
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(slug, categoria.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", categoriaPadre=" + (categoriaPadre != null ? categoriaPadre.getId() : null) +
                ", nome='" + nome + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
