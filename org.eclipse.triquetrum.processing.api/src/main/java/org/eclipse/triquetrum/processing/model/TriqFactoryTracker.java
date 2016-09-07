package org.eclipse.triquetrum.processing.model;

/**
 * A simple solution to easily allow access to the default {@link TriqFactory}
 * implementation instance that should be available in a Triquetrum runtime.
 * <p>
 * This avoids the complexity where everything that needs to access the factory instance
 * should implement one of the OSGi service retrieval methods (Activators with ServiceTrackers, DS, ...).
 * </p>
 * <p>
 * It also allows an easy access from places where the DS component model can not be applied
 * (e.g. from inside Actors whose creation life cycle can not be under the control of DS).
 * </p>
 * <p>
 * Remark that in advanced runtime implementations, multiple factories might be present.
 * E.g. one that constructs JPA entities to support persistent Task processing traces, another for simple in-memory representations etc.
 * This simple tracker approach does not provide for such cases, i.e. it only manages a single factory instance that was configured to be the default one.
 * To access other factory instances, some specific coding will still be needed.
 * </p>
 */
public class TriqFactoryTracker {

  // the configured default factory instance for active runtime
  private static TriqFactory factory = null;

  /**
   * This method provides the ease-of-access on the runtime's default factory instance.
   *
   * @return the single registered processing model factory instance of this Triquetrum runtime.
   */
	public static TriqFactory getDefaultFactory() {
		return(factory);
	}

  /**
   * Set the default factory instance for this Triquetrum runtime.
   * <p>
   * This method can be invoked e.g. from an activation method of the factory instance,
   * invoked by DS when the instance is created as a DS component.
   * </p>
   *
   * @param factory the processing model factory instance that should be used as default factory in this Triquetrum runtime.
   */
	public static void setDefaultFactory(TriqFactory factory) {
	  TriqFactoryTracker.factory = factory;
	}

  /**
   * Unsets the given factory as the default factory instance for this Triquetrum runtime.
   * <p>
   * This method can be invoked e.g. from a deactivation method of the factory instance,
   * invoked by DS when the instance is destroyed as a DS component.
   * </p>
   * Post-condition of this method is either a null default factory instance (if the given factory matches the current default one),
   * or the unchanged instance, if the given factory was not set as the default one.
   *
   * @param factory the processing model factory instance that should no longer be used as default factory in this Triquetrum runtime.
   */
  public static void unsetDefaultFactory(TriqFactory factory) {
    if((TriqFactoryTracker.factory != null) && (TriqFactoryTracker.factory == factory)) {
      TriqFactoryTracker.factory = null;
    }
  }
}
