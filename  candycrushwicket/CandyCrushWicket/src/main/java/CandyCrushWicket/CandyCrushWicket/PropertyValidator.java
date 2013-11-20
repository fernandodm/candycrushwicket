package CandyCrushWicket.CandyCrushWicket;

import java.lang.reflect.InvocationTargetException;

import org.apache.wicket.Component;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.IValidatorAddListener;
import org.apache.wicket.validation.ValidationError;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.ReflectionUtils;

/**
 * {@link IValidator} genérico que se puede utilizar para validar cualquier
 * propiedad. Para esto, se debe seguir una convención: 
 * 
 * Toda property podrá
 * - tener, además de un getter y un setter, un método público sin valor de
 * retorno (void) que reciba un único parámetro del mismo tipo que la property,
 * cuyo nombre comience con "validar" y siga con el nombre de la property con la
 * primer letra en mayúscula. dicha función deberá lanzar UserException en caso
 * en que el valor dado no cumpla con las reglas que definen la validez de los
 * valores de la property.
 * 
 * Ejemplos:
 * 
 * property "saldo" de tipo Double -> validarSaldo(Double saldo) property "edad"
 * de tipo "Integer" -> validarEdad(Integer edad) property "telefono" de tipo
 * "String" -> validarTelefono(String telefono)
 * 
 * @author jfernandes
 */
public final class PropertyValidator implements IValidator<String>, IValidatorAddListener{
	private Component component;
	private String propertyName;

	public void onAdded(Component component) {
		this.component = component;
		this.propertyName = component.getId();
	}
	
	public void validate(IValidatable<String> validatable) {
		try {
			Object model = component.getParent().getDefaultModelObject();
			ReflectionUtils.invokeMethod(model,	getValidatePropertyMethodName(), validatable.getValue());
		} catch (RuntimeException e) {
			if (isUserException(e)) {
				ValidationError error = new ValidationError();
				error.setMessage(e.getCause().getCause().getMessage() + " ");
				validatable.error(error);
			} else {
				throw e;
			}
		}
	}
	
	protected String getValidatePropertyMethodName() {
		return "validar" + Character.toUpperCase(this.propertyName.charAt(0))
				+ this.propertyName.substring(1);
	}

	protected boolean isUserException(RuntimeException e) {
		return e.getCause() instanceof InvocationTargetException	&& e.getCause().getCause() instanceof UserException;
	}
	


}