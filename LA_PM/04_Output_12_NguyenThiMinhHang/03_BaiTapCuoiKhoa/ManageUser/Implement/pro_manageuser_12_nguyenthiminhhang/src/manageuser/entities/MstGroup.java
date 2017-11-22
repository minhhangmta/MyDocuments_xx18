/**
* Copyright(C) 2017 Luvina
* MstGroup.java, Oct 26, 2017 minhhang
*/
package manageuser.entities;

/**
 * Lớp đối tượng MstGroup
 * 
 * @author minhhang
 */
public class MstGroup {
	private int groupId;
	private String groupName;

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 
	 */
	public MstGroup() {
		super();
	}
}
