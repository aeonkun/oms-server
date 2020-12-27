//package com.ohwow.oms.products.dto;
//
//import java.time.LocalDateTime;
//
//public class ProductDetailWithTransactionDto extends ProductDto {
//
//	private final ProductTransactionDto productTransactionDto;
//
//	public ProductDetailWithTransactionDto(long id, String itemName, long price, String createdBy, int stockOnHand,
//			LocalDateTime dateTimeCreated, String modifiedBy, LocalDateTime dateTimeModified,
//			ProductTransactionDto productTransactionDto) {
//		super(id, itemName, price, createdBy, stockOnHand, dateTimeCreated, modifiedBy, dateTimeModified);
//		this.productTransactionDto = productTransactionDto;
//
//		// TODO Auto-generated constructor stub
//	}
//
//	public ProductTransactionDto getProductTransactionDto() {
//		return productTransactionDto;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((productTransactionDto == null) ? 0 : productTransactionDto.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ProductDetailWithTransactionDto other = (ProductDetailWithTransactionDto) obj;
//		if (productTransactionDto == null) {
//			if (other.productTransactionDto != null)
//				return false;
//		} else if (!productTransactionDto.equals(other.productTransactionDto))
//			return false;
//		return true;
//	}
//
//}
