package layers;

import enumerations.Key;
import handlers.OptionsHandler;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public abstract class Layer extends JPanel {

  private Layer prev;
  private Layer next;

  private boolean active;
  private boolean visible;

  /**
   * ContentLayer.
   */
  public Layer() {
    super();
    setPreferredSize( new Dimension(OptionsHandler.getInstance().getWidth(),
        OptionsHandler.getInstance().getHeight()));
    prev = null;
    next = null;
    active = true;
    visible = true;
  }

  private void setPrevious(Layer prev) {
    this.prev = prev;
  }

  /**
   * Adds a layer to the image.
   */
  public void addLayer(Layer layer) {
    this.next = layer;
    if (layer != null) {
      layer.setPrevious(this);
    }
  }

  /**
   * Remove a layer.
   */
  public void removeLayer() {
    if (prev != null) {
      prev.addLayer(next);
    }
    prev = null;
    next = null;
  }

  public boolean isActive() {
    return active;
  }

  public void activate() {
    active = true;
  }

  public void deactivate() {
    active = false;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public void updateLayer() {
    update();
    updateNext();
  }

  protected abstract void update();

  private void updateNext() {
    if (next != null) {
      next.updateLayer();
    }
  }

  /**
   * Draws the layer to the image.
   */
  public void drawLayer(Graphics2D graphic) {
    if (visible) {
      graphic = draw(graphic);
    }
    drawNext(graphic);
  }

  protected abstract Graphics2D draw(Graphics2D graphic);

  private void drawNext(Graphics2D graphic) {
    if (next != null) {
      next.drawLayer(graphic);
    }
  }

  public void handleKeyPress(Key key) {
    keyPressed(key);
    passKeyPress(key);
  }

  public void handleKeyRelease(Key key) {
    keyReleased(key);
    passKeyRelease(key);
  }

  protected abstract void keyPressed(Key key);

  protected abstract void keyReleased(Key key);

  private void passKeyPress(Key key) {
    if (next != null) {
      next.handleKeyPress(key);
    }
  }

  private void passKeyRelease(Key key) {
    if (next != null) {
      next.handleKeyRelease(key);
    }
  }
}
