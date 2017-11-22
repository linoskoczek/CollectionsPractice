package Groups;

import Storing.GroupMembers;

public class StudentGroup {
    private String name;
    private GroupMembers groupMembers;

    public StudentGroup(String name) {
        this.name = name;
        groupMembers = new GroupMembers(this);
    }

    public String getName() {
        return name;
    }

    public void setGroupMembers(GroupMembers gd) {
        this.groupMembers = gd;
    }

    public GroupMembers getGroupMembers() {
        return groupMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentGroup that = (StudentGroup) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return groupMembers != null ? groupMembers.equals(that.groupMembers) : that.groupMembers == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (groupMembers != null ? groupMembers.hashCode() : 0);
        return result;
    }

}