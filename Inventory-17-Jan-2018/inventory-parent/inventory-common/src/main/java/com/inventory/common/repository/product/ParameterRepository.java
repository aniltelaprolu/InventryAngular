/**
 * 
 */
package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.product.Parameter;

/**
 * @author ES002
 *
 */
@Repository("parameterRepository")
public interface ParameterRepository extends JpaRepository<Parameter, Long>{

}
