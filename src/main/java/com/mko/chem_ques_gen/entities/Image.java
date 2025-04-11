package com.mko.chem_ques_gen.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private int widthInCm;
    private int heightInCm;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    // Constructors
    public Image() {}
    
    public Image(String name, String type, byte[] imageData, int width, int height) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.widthInCm = width;
        this.heightInCm = height;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public int getWidthInCm() {
		return widthInCm;
	}

	public void setWidthInCm(int widthInCm) {
		this.widthInCm = widthInCm;
	}

	public int getHeightInCm() {
		return heightInCm;
	}

	public void setHeightInCm(int heightInCm) {
		this.heightInCm = heightInCm;
	}

    // Getters and Setters
    
}
