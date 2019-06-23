/*
 * Scala (https://www.scala-lang.org)
 *
 * Copyright EPFL and Lightbend, Inc.
 *
 * Licensed under Apache License 2.0
 * (http://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package scala.tools.ant.sabbus

import java.io.File

import org.apache.tools.ant.types.Path

class Settings {

  private var gBf: Option[String] = None
  def g: String = gBf.get
  def g_=(s: String): this.type = { gBf = Some(s); this }

  private var uncheckedBf: Boolean = false
  def unchecked: Boolean = uncheckedBf
  def unchecked_=(b: Boolean): this.type = { uncheckedBf = b; this }

  private var classpathBf: Option[Path] = None
  def classpath: Path = classpathBf.get
  def classpath_=(p: Path): this.type = { classpathBf = Some(p); this }

  private var sourcepathBf: Option[Path] = None
  def sourcepath: Path = sourcepathBf.get
  def sourcepath_=(p: Path): this.type = { sourcepathBf = Some(p); this }

  private var sourcedirBf: Option[File] = None
  def sourcedir: File = sourcedirBf.get
  def sourcedir_=(p: File): this.type = { sourcedirBf = Some(p); this }

  private var bootclasspathBf: Option[Path] = None
  def bootclasspath: Path = bootclasspathBf.get
  def bootclasspath_=(p: Path): this.type = { bootclasspathBf = Some(p); this }

  private var extdirsBf: Option[Path] = None
  def extdirs: Path = extdirsBf.get
  def extdirs_=(p: Path): this.type = { extdirsBf = Some(p); this }

  private var dBf: Option[File] = None
  def d: File = dBf.get
  def d_=(f: File): this.type = { dBf = Some(f); this }

  private var encodingBf: Option[String] = None
  def encoding: String = encodingBf.get
  def encoding_=(s: String): this.type = { encodingBf = Some(s); this }

  private var targetBf: Option[String] = None
  def target: String = targetBf.get
  def target_=(s: String): this.type = { targetBf = Some(s); this }

  private var optimiseBf: Boolean = false
  def optimise: Boolean = optimiseBf
  def optimise_=(b: Boolean): Unit = { optimiseBf = b }

  private var extraParamsBf: Seq[String] = Seq()
  def extraParams: Seq[String] = extraParamsBf
  def extraParams_=(s: Seq[String]): this.type = { extraParamsBf = s; this }

  def toArgs: List[String] =
    (if (gBf.isDefined) "-g:"+g :: Nil else Nil) :::
    (if (uncheckedBf) "-unchecked" :: Nil else Nil) :::
    (if (classpathBf.isDefined) "-classpath" :: classpath.toString :: Nil else Nil) :::
    (if (sourcepathBf.isDefined) "-sourcepath" :: sourcepath.toString :: Nil else Nil) :::
    (if (sourcedirBf.isDefined) "-Xsourcedir" :: sourcedir.toString :: Nil else Nil) :::
    (if (bootclasspathBf.isDefined) "-bootclasspath" :: bootclasspath.toString :: Nil else Nil) :::
    (if (extdirsBf.isDefined) "-extdirs" :: extdirs.toString :: Nil else Nil) :::
    (if (dBf.isDefined) "-d" :: d.getAbsolutePath :: Nil else Nil) :::
    (if (encodingBf.isDefined) "-encoding" :: encoding :: Nil else Nil) :::
    (if (targetBf.isDefined) "-target:"+target :: Nil else Nil) :::
    (if (optimiseBf) "-optimise" :: Nil else Nil) :::
    extraParamsBf.toList

  override def equals(that: Any): Boolean = that match {
    case cs: Settings =>
      this.gBf == cs.gBf &&
      this.uncheckedBf == cs.uncheckedBf &&
      this.classpathBf == cs.classpathBf &&
      this.sourcepathBf == cs.sourcepathBf &&
      this.sourcedirBf == cs.sourcedirBf &&
      this.bootclasspathBf == cs.bootclasspathBf &&
      this.extdirsBf == cs.extdirsBf &&
      this.dBf == cs.dBf &&
      this.encodingBf == cs.encodingBf &&
      this.targetBf == cs.targetBf &&
      this.optimiseBf == cs.optimiseBf &&
      this.extraParamsBf == cs.extraParamsBf
    case _ => false
  }

  override lazy val hashCode: Int = Seq[Any](
    gBf,
    uncheckedBf,
    classpathBf,
    sourcepathBf,
    sourcedirBf,
    bootclasspathBf,
    extdirsBf,
    dBf,
    encodingBf,
    targetBf,
    optimiseBf,
    extraParamsBf
  ).##
}
