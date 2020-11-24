package cz.zcu.fav.pia.jsf.support;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;

import org.apache.myfaces.renderkit.html.HtmlFormRenderer;

@FacesRenderer(
		rendererType = "javax.faces.Form",
		componentFamily = "javax.faces.Form"
)
public class CsrfFormRenderer extends HtmlFormRenderer {

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final var elContext = context.getELContext();
        final var expFactory = context.getApplication().getExpressionFactory();

        final var writer = context.getResponseWriter();
        writer.startElement("input", component);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("name", expFactory.createValueExpression(elContext, "${_csrf.parameterName}", String.class).getValue(elContext), null);
        writer.writeAttribute("value", expFactory.createValueExpression(elContext, "${_csrf.token}", String.class).getValue(elContext), null);
        writer.endElement("input");
        writer.write("\n");
        super.encodeEnd(context, component);
    }

}
