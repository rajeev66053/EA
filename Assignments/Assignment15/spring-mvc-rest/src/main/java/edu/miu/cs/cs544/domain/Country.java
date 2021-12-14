package edu.miu.cs.cs544.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Country")
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({ 
	@NamedQuery(name = "Country.All", query = "from Country") 
})
public class Country {
	
	@Id
	@Column(name = "Country_id")
	@EqualsAndHashCode.Include
	private String id;
	
	private String country;
	
	@OneToMany(mappedBy = "country")
//	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<City> cities;
	
}
