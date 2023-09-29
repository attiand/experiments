package convert;

import java.util.Objects;

public record SlaskDTO(boolean isCourse, String name) {


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SlaskDTO slask = (SlaskDTO) o;
		return isCourse == slask.isCourse && Objects.equals(name, slask.name);
	}

}
