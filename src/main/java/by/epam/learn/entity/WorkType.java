package by.epam.learn.entity;

/**
 * The {@code WorkType} class describes the entity type of work
 * 
 * @author Ihar Klepcha
 */
public class WorkType extends Entity {
	private static final long serialVersionUID = 1L;
	private long workTypeId;
	private String workType;
	
	/**
	 * Constructs a new type of work
	 */
	public WorkType() {
	}

	/**
	 * Constructs a new type of work with the specified
	 * 
	 * @param workTypeId {@link long} type id of work
	 * @param workType {@link String} type name of work
	 */
	public WorkType(long workTypeId, String workType) {
		this.workTypeId = workTypeId;
		this.workType = workType;
	}

	public long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((workType == null) ? 0 : workType.hashCode());
		result = prime * result + (int) (workTypeId ^ (workTypeId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkType other = (WorkType) obj;
		if (workType == null) {
			if (other.workType != null)
				return false;
		} else if (!workType.equals(other.workType))
			return false;
		if (workTypeId != other.workTypeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nWork type{ Work type ID = ").append(workTypeId);
		sb.append(", work type = ").append(workType).append(" }");
		return sb.toString();
	}
}
