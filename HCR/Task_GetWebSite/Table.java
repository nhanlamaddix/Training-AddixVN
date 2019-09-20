
public class Table {
        private int numTable;
        private int page;
        private String companyName;
        private String titleElement;
        private String salary;
        private String timeWorking;
        private String traffic;

        public Table(){
            this.numTable = 0;
            this.page = 0;
            this.companyName = "";
            this.titleElement = "";
            this.salary = "";
            this.timeWorking = "";
            this.traffic = "";
        }

        public Table(int numTable, int page, String companyName, String titleElement, String salary, String timeWorking, String traffic){
            this.numTable = numTable;
            this.page = page;
            this.companyName = companyName;
            this.titleElement = titleElement;
            this.salary = salary;
            this.timeWorking = timeWorking;
            this.traffic = traffic;
        }

        
        public int getNumTable() {
            return numTable;
        }

        public void setNumTable(int numTable) {
            this.numTable = numTable;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getTitleElement() {
            return titleElement;
        }

        public void setTitleElement(String titleElement) {
            this.titleElement = titleElement;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getTimeWorking() {
            return timeWorking;
        }

        public void setTimeWorking(String timeWorking) {
            this.timeWorking = timeWorking;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }
}
