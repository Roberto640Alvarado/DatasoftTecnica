package com.example.demo.models.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Books {
	@Id 
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
	private String name;

    @Column(name = "summary")
    private String summary;

    @Column(name = "price")
    private Float price;

    @Column(name = "state", insertable = false)
    private Boolean state;
    
	@Column(name = "image")
	private String image;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gen_id", nullable = true)
    private Generes generes;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id", nullable = true)
    private User users;
    

	public Books(Integer id, String name, String summary, Float price, Boolean state, String image) {
		super();
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.price = price;
		this.state = state;
		this.image = image;
		
	}

}
