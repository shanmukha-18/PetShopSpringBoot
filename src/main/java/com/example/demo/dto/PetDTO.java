package com.example.demo.dto;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;
//
//public class PetDTO {
//	   private int petId;
//	   @NotBlank(message = "Name is mandatory")
//	    private String name;
//	   @NotBlank(message = "Breed is mandatory")
//	    private String breed;
//	   @Min(value = 0, message = "Age must be a non-negative integer")
//	    private int age;
//	   @Positive(message = "Price must be a positive integer")
//	    private int price;
//	   @NotNull(message = "Category ID is mandatory")
//	    @Positive(message = "Category ID must be a positive integer")
//	    private int categoryId;
//	   @NotBlank(message = "Description is mandatory")
//	    private String description;
//	    private String imageUrl;
//	    
//		public int getPetId() {
//			return petId;
//		}
//		public void setPetId(int petId) {
//			this.petId = petId;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getBreed() {
//			return breed;
//		}
//		public void setBreed(String breed) {
//			this.breed = breed;
//		}
//		public int getAge() {
//			return age;
//		}
//		public void setAge(int age) {
//			this.age = age;
//		}
//		public int getPrice() {
//			return price;
//		}
//		public void setPrice(int price) {
//			this.price = price;
//		}
//		public int getCategoryId() {
//			return categoryId;
//		}
//		public void setCategoryId(int categoryId) {
//			this.categoryId = categoryId;
//		}
//		public String getDescription() {
//			return description;
//		}
//		public void setDescription(String description) {
//			this.description = description;
//		}
//		public String getImageUrl() {
//			return imageUrl;
//		}
//		public void setImageUrl(String imageUrl) {
//			this.imageUrl = imageUrl;
//		}
//		@Override
//		public String toString() {
//			return "PetDTO [petId=" + petId + ", name=" + name + ", breed=" + breed + ", age=" + age + ", price="
//					+ price + ", categoryId=" + categoryId + ", description=" + description + ", imageUrl=" + imageUrl
//					+ "]";
//		}
//		public PetDTO(int petId, String name, String breed, int age, int price, int categoryId, String description,
//				String imageUrl) {
//			super();
//			this.petId = petId;
//			this.name = name;
//			this.breed = breed;
//			this.age = age;
//			this.price = price;
//			this.categoryId = categoryId;
//			this.description = description;
//			this.imageUrl = imageUrl;
//		}
//		
//		public PetDTO() {
//			
//
//	}
//	    
//	    
//	 	  
//}
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;
//
//public class PetDTO {
//    private int petId;
//
//    @NotBlank(message = "Name is mandatory")
//    private String name;
//
//    @NotBlank(message = "Breed is mandatory")
//    private String breed;
//
//    @Min(value = 0, message = "Age must be a non-negative integer")
//    private int age;
//
//    @Positive(message = "Price must be a positive integer")
//    private int price;
//
//    @NotBlank(message = "Category name is mandatory")
//    private String categoryName;
//
//    @NotBlank(message = "Description is mandatory")
//    private String description;
//
//    private String imageUrl;
//
//    // constructors, getters, and setters
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public PetDTO() {
//	}
//
//	public PetDTO(int petId, @NotBlank(message = "Name is mandatory") String name,
//			@NotBlank(message = "Breed is mandatory") String breed,
//			@Min(value = 0, message = "Age must be a non-negative integer") int age,
//			@Positive(message = "Price must be a positive integer") int price,
//			@NotBlank(message = "Category name is mandatory") String categoryName,
//			@NotBlank(message = "Description is mandatory") String description, String imageUrl) {
//		this.petId = petId;
//		this.name = name;
//		this.breed = breed;
//		this.age = age;
//		this.price = price;
//		this.categoryName = categoryName;
//		this.description = description;
//		this.imageUrl = imageUrl;
//	}
//
//	public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//	public int getPetId() {
//		return petId;
//	}
//
//	public void setPetId(int petId) {
//		this.petId = petId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getBreed() {
//		return breed;
//	}
//
//	public void setBreed(String breed) {
//		this.breed = breed;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getImageUrl() {
//		return imageUrl;
//	}
//
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}
//}


//package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public class PetDTO {

	
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Breed is mandatory")
    private String breed;

    @Min(value = 0, message = "Age must be a non-negative integer")
    private int age;

    @Positive(message = "Price must be a positive integer")
    private int price;

    @NotBlank(message = "Category name is mandatory")
    @NotNull
    private String categoryName;

    private String description;
    
    private String imageUrl;

    private List<String> petFoodNames;
    private List<String> vaccinationNames;
    private List<String> supplierNames;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<String> getPetFoodNames() {
		return petFoodNames;
	}
	public void setPetFoodNames(List<String> petFoodNames) {
		this.petFoodNames = petFoodNames;
	}
	public List<String> getVaccinationNames() {
		return vaccinationNames;
	}
	public void setVaccinationNames(List<String> vaccinationNames) {
		this.vaccinationNames = vaccinationNames;
	}
	public List<String> getSupplierNames() {
		return supplierNames;
	}
	public void setSupplierNames(List<String> supplierNames) {
		this.supplierNames = supplierNames;
	}
    
    
    
	
    // Getters and setters

}
