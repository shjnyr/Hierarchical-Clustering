
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class HierarchicalClusteringPanel extends JPanel
{
    private ArrayList<Cluster> clusters = new ArrayList();
    public HierarchicalClusteringPanel(ArrayList<Point> points)
    {
        for (Point p : points)
            clusters.add(new Cluster(p));
        
        
        int temp = 0;
        
        while (temp < 9 && clusters.size() != 1)
        {
            Cluster A = clusters.get(0);
            Cluster B = clusters.get(0);
            double dis = 1000000000, temp_dis;
            for (int i = 0; i < clusters.size(); i++)
                for (int j = 0; j < clusters.size(); j++)
                {
                    if (j != i)
                    {
                        temp_dis = Math.sqrt(Math.pow(clusters.get(i).centroid.x() - clusters.get(j).centroid.x(), 2) + Math.pow(clusters.get(i).centroid.y() - clusters.get(j).centroid.y(), 2));
                        if (temp_dis < dis)
                        {
                            dis = temp_dis;
                            A = clusters.get(i);
                            B = clusters.get(j);
                        }
                    }
                }
            
            clusters.remove(A);
            clusters.remove(B);
            
            clusters.add(new Cluster(A, B));
            temp++;
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        for (Cluster c : clusters)
            c.paintComponent(g);
    }
}
