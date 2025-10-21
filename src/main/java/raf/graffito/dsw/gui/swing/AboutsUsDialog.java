package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.model.TeamMember;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
public class AboutsUsDialog extends JDialog {

    private final JPanel content = new JPanel(new GridLayout(0,3,16,16));

    public AboutsUsDialog(Frame owner) {
        super(owner, "About Us", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(owner);

        setLayout(new BorderLayout());
        add(new JScrollPane(content), BorderLayout.CENTER);

    }

    public void renderMembers(List<TeamMember> members){
        content.removeAll();
        for(TeamMember m : members){
            content.add(cardFor(m));
        }
    }

    private JComponent cardFor(TeamMember m){
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JLabel img = new JLabel(loadIcon(m.getImagePath(), 140, 140), SwingConstants.CENTER);
        JLabel name = new JLabel(m.getName(), SwingConstants.CENTER);
        name.setFont(name.getFont().deriveFont(Font.BOLD, 14f));
        JLabel index = new JLabel(m.getIndex(), SwingConstants.CENTER);

        p.add(name, BorderLayout.NORTH);
        p.add(img, BorderLayout.CENTER);
        p.add(index, BorderLayout.SOUTH);
        return p;
    }

    private Icon loadIcon(String path, int w, int h){
        URL url = getClass().getResource(path);
        Image img = (url != null) ? new ImageIcon(url).getImage() : new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        return new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }
}
